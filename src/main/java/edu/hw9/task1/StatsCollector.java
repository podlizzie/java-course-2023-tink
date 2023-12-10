package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    private final ConcurrentHashMap<String, List<Double>> metricsMap = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        metricsMap.compute(metricName, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }
            Arrays.stream(values).forEach(list::add);
            return list;
        });
    }

    public Map<String, Map<MetricType, Double>> stats() {
        Map<String, Map<MetricType, Double>> stats = new HashMap<>();
        metricsMap.forEach((metricName, values) -> {
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = -Double.MAX_VALUE;

            for (double value : values) {
                sum += value;
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
            }

            double average = sum / values.size();
            Map<MetricType, Double> metricStats = new HashMap<>();
            metricStats.put(MetricType.SUM, sum);
            metricStats.put(MetricType.AVERAGE, average);
            metricStats.put(MetricType.MIN, min);
            metricStats.put(MetricType.MAX, max);

            stats.put(metricName, metricStats);
        });
        return stats;
    }
}
