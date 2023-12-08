package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class MetricAggregator {
    private final AtomicReference<Double> min = new AtomicReference<>(Double.MAX_VALUE);
    private final AtomicReference<Double> max = new AtomicReference<>(Double.MIN_VALUE);
    private double sum = 0;
    private long count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void push(double[] values) {
        lock.lock();
        try {
            for (double value : values) {
                sum += value;
                count++;

                // Use AtomicReference to ensure thread-safe min/max updates
                min.accumulateAndGet(value, Math::min);
                max.accumulateAndGet(value, Math::max);
            }
        } finally {
            lock.unlock();
        }
    }

    public Map<MetricType, Double> getStatistics() {
        lock.lock();
        try {
            double average = count == 0 ? 0 : sum / count;
            Map<MetricType, Double> stats = new ConcurrentHashMap<>();
            stats.put(MetricType.MIN, min.get());
            stats.put(MetricType.MAX, max.get());
            stats.put(MetricType.AVERAGE, average);
            stats.put(MetricType.SUM, sum);
            return stats;
        } finally {
            lock.unlock();
        }
    }
}
