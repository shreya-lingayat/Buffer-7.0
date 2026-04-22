package com.greencode.service;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.greencode.analysis.BusyWaitVisitor;
import com.greencode.analysis.LoopInvariantVisitor;
import com.greencode.analysis.NestedLoopVisitor;
import com.greencode.logic.CO2Estimator;
import com.greencode.logic.GreenScoreCalculator;
import com.greencode.model.AnalysisResult;
import com.greencode.model.SmellResult;
import com.greencode.model.SuggestionEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisService {

    private final SuggestionService suggestionService;

    public AnalysisService(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    public AnalysisResult analyze(String sourceCode) {
        List<SmellResult> smells = new ArrayList<>();
        Map<String, SuggestionEntry> suggestions = new HashMap<>();
        
        try {
            CompilationUnit cu = StaticJavaParser.parse(sourceCode);
            
            // Run all visitors
            cu.accept(new NestedLoopVisitor(), smells);
            cu.accept(new LoopInvariantVisitor(), smells);
            cu.accept(new BusyWaitVisitor(), smells);
            cu.accept(new com.greencode.analysis.StringConcatVisitor(), smells);
            cu.accept(new com.greencode.analysis.BoxingVisitor(), smells);
            cu.accept(new com.greencode.analysis.ResourceLeakVisitor(), smells);
            cu.accept(new com.greencode.analysis.CollectionEfficiencyVisitor(), smells);
            
            // Populate suggestions for found smells
            for (SmellResult smell : smells) {
                String key = smell.suggestionKey();
                if (!suggestions.containsKey(key)) {
                    suggestions.put(key, suggestionService.getSuggestion(key));
                }
            }
            
        } catch (Exception e) {
            // In a real scenario, we'd handle parsing errors specifically.
        }

        int score = GreenScoreCalculator.calculateScore(smells);
        String grade = GreenScoreCalculator.calculateGrade(score);
        double co2 = CO2Estimator.estimateCO2(smells);

        return new AnalysisResult(smells, score, grade, co2, suggestions);
    }
}
