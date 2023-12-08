package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    private final Map<String, MetricAggregator> metrics = new ConcurrentHashMap<>();

    public void push(String metricName, double[] values) {
        MetricAggregator aggregator = metrics.computeIfAbsent(metricName, k -> new MetricAggregator());
        aggregator.push(values);
    }

    public Map<String, Map<MetricType, Double>> stats() {
        Map<String, Map<MetricType, Double>> statsMap = new ConcurrentHashMap<>();
        for (Map.Entry<String, MetricAggregator> entry : metrics.entrySet()) {
            statsMap.put(entry.getKey(), entry.getValue().getStatistics());
        }
        return statsMap;
    }
}
