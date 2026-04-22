package com.greencode.logic;

import com.greencode.model.SmellResult;
import java.util.List;

public class GreenScoreCalculator {

    private static final double PENALTY_MULTIPLIER = 2.0;
    private static final int CHAOS_THRESHOLD = 5;
    private static final int CHAOS_PENALTY = 10;

    public static int calculateScore(List<SmellResult> smells) {
        double rawPenalty = smells.stream()
                .mapToInt(SmellResult::severity)
                .sum() * PENALTY_MULTIPLIER;
        
        int totalPenalty = (int) Math.round(rawPenalty);
        
        // Add "Environmental Chaos" penalty for messy files
        if (smells.size() > CHAOS_THRESHOLD) {
            totalPenalty += CHAOS_PENALTY;
        }
        
        return Math.max(0, 100 - totalPenalty);
    }

    public static String calculateGrade(int score) {
        if (score >= 98) return "A+"; // Elite
        if (score >= 92) return "A";  // Excellent
        if (score >= 82) return "B";  // Good
        if (score >= 70) return "C";  // Acceptable
        if (score >= 55) return "D";  // Poor
        return "F"; // Ecological Failure
    }
}
