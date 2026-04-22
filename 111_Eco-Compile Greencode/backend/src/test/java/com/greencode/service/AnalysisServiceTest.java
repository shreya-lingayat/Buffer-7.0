package com.greencode.service;

import com.greencode.model.AnalysisResult;
import com.greencode.model.SmellType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnalysisServiceTest {

    private final SuggestionService suggestionService = new SuggestionService();
    private final AnalysisService analysisService;

    public AnalysisServiceTest() {
        suggestionService.loadSuggestions(); // Manually trigger YAML loading
        analysisService = new AnalysisService(suggestionService);
    }

    @Test
    void testCleanCode() {
        String code = "public class Test { public void hello() { System.out.println(\"Hello\"); } }";
        AnalysisResult result = analysisService.analyze(code);
        
        assertEquals(100, result.score());
        assertEquals("A+", result.grade());
        assertTrue(result.smells().isEmpty());
    }

    @Test
    void testNestedLoop() {
        String code = "public class Test { public void loop() { " +
                      "for(int i=0; i<10; i++) { for(int j=0; j<10; j++) { System.out.println(i); } } " +
                      "} }";
        AnalysisResult result = analysisService.analyze(code);
        
        assertFalse(result.smells().isEmpty());
        assertTrue(result.smells().stream().anyMatch(s -> s.smellType() == SmellType.NESTED_LOOP));
        assertTrue(result.score() < 100);
    }

    @Test
    void testBusyWait() {
        String code = "public class Test { public void waitLoop() { " +
                      "while(true) { } " +
                      "} }";
        AnalysisResult result = analysisService.analyze(code);
        
        assertTrue(result.smells().stream().anyMatch(s -> s.smellType() == SmellType.BUSY_WAIT));
        assertEquals(10, result.smells().stream()
                .filter(s -> s.smellType() == SmellType.BUSY_WAIT)
                .findFirst().get().severity());
    }
}
