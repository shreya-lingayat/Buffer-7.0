package com.greencode.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.greencode.model.SuggestionEntry;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class SuggestionService {

    private Map<String, SuggestionEntry> suggestions = new HashMap<>();

    @PostConstruct
    public void loadSuggestions() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            InputStream inputStream = new ClassPathResource("suggestions.yaml").getInputStream();
            suggestions = mapper.readValue(inputStream, new TypeReference<Map<String, SuggestionEntry>>() {});
        } catch (IOException e) {
            // Log error or handle fallback
            System.err.println("Error loading suggestions.yaml: " + e.getMessage());
        }
    }

    public SuggestionEntry getSuggestion(String key) {
        return suggestions.getOrDefault(key, null);
    }

    public Map<String, SuggestionEntry> getAllSuggestions() {
        return suggestions;
    }
}
