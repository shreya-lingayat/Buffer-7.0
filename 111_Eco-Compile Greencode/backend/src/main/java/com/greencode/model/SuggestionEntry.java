package com.greencode.model;

public class SuggestionEntry {
    private String bigO;
    private String description;
    private String codeSnippetBefore;
    private String codeSnippetAfter;

    public SuggestionEntry() {}

    public SuggestionEntry(String bigO, String description, String codeSnippetBefore, String codeSnippetAfter) {
        this.bigO = bigO;
        this.description = description;
        this.codeSnippetBefore = codeSnippetBefore;
        this.codeSnippetAfter = codeSnippetAfter;
    }

    public String getBigO() { return bigO; }
    public void setBigO(String bigO) { this.bigO = bigO; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCodeSnippetBefore() { return codeSnippetBefore; }
    public void setCodeSnippetBefore(String codeSnippetBefore) { this.codeSnippetBefore = codeSnippetBefore; }

    public String getCodeSnippetAfter() { return codeSnippetAfter; }
    public void setCodeSnippetAfter(String codeSnippetAfter) { this.codeSnippetAfter = codeSnippetAfter; }
}
