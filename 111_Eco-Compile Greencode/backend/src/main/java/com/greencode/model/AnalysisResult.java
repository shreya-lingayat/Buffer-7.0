package com.greencode.model;

import java.util.List;
import java.util.Map;

public record AnalysisResult(
    List<SmellResult> smells,
    int score,
    String grade,
    double co2EstimateGrams,
    Map<String, SuggestionEntry> suggestions
) {}
