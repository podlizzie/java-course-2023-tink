package edu.hw9;

import edu.hw9.task1.MetricType;
import edu.hw9.task1.StatsCollector;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    StatsCollector statsCollector = new StatsCollector();

    private static Stream<Arguments> testStatsData() {
        return Stream.of(
            Arguments.of("1", new double[] {1.0, 2.0, 3.0, 4.0}, MetricType.AVERAGE, 2.5),
            Arguments.of("2", new double[] {50.0, 100.0, 150.0}, MetricType.SUM, 300.0),
            Arguments.of("3", new double[] {-5.0, 0.0, 5.0}, MetricType.MIN, -5.0),
            Arguments.of("4", new double[] {1.65, 1.90, 1.72}, MetricType.MAX, 1.90),
            Arguments.of("5", new double[] {1.0, 2.0, 3.0}, MetricType.AVERAGE, 2.0),
            Arguments.of("6", new double[] {4.0, 5.0, 6.0}, MetricType.AVERAGE, 5.0)
        );
    }

    @ParameterizedTest
    @MethodSource("testStatsData")
    public void testStatsCollector(String metricName, double[] values, MetricType metricType, double expectedValue) {
        // when
        statsCollector.push(metricName, values);
        Map<String, Map<MetricType, Double>> stats = statsCollector.stats();

        // then
        assertThat(stats.get(metricName).get(metricType)).isEqualTo(expectedValue);
    }
}
