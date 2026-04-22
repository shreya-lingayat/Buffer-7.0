package com.greencode.logic;

import com.greencode.model.SmellResult;
import java.util.List;

public class CO2Estimator {

    // Assumption: Each severity point roughly translates to 0.5g of CO2 per year
    // based on average cloud server CPU usage and regional carbon intensity.
    private static final double EMISSION_FACTOR = 0.5;

    public static double estimateCO2(List<SmellResult> smells) {
        int totalSeverity = smells.stream()
                .mapToInt(SmellResult::severity)
                .sum();
        
        return totalSeverity * EMISSION_FACTOR;
    }
}
