package com.roomify.roomifybackend.utils.helpers;

public class MetricsHelper {

    private MetricsHelper(){}

    public static Double calculateGrowth(Number previous, Number current) {
        double prev = previous == null ? 0.0 : previous.doubleValue();
        double curr = current == null ? 0.0 : current.doubleValue();

        if (prev == 0.0) {
            if (curr == 0.0) {
                return 0.0; // No cambio
            } else {
                return 100.0; // Crecimiento desde cero (caso especial)
            }
        }

        return ((curr - prev) / prev) * 100.0;
    }


}
