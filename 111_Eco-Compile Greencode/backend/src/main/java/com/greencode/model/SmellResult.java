package com.greencode.model;

public record SmellResult(
    SmellType smellType,
    int lineNumber,
    int severity,
    String suggestionKey
) {}
