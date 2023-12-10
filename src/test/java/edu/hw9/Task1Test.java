package edu.hw9;

import edu.hw9.task1.MetricType;
import edu.hw9.task1.StatsCollector;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    StatsCollector statsCollector = new StatsCollector();

    @Test
    public void testThatAverageIsReturnedCorrectly() {
        // given
        String metricName = "1";
        double[] values = {1.0, 2.0, 3.0, 4.0};

        // when
        statsCollector.push(metricName, values);
        Map<String, Map<MetricType, Double>> stats = statsCollector.stats();

        // then
        assertThat(stats.get(metricName).get(MetricType.AVERAGE)).isEqualTo(2.5);
    }

    @Test
    public void testThatSumIsReturnedCorrectly() {
        // given
        String metricName = "2";
        double[] values = {50.0, 100.0, 150.0};

        // when
        statsCollector.push(metricName, values);
        Map<String, Map<MetricType, Double>> stats = statsCollector.stats();

        // then
        assertThat(stats.get(metricName).get(MetricType.SUM)).isEqualTo(300.0);
    }

    @Test
    public void testThatMinIsReturnedCorrectly() {
        // given
        String metricName = "3";
        double[] values = {-5.0, 0.0, 5.0};

        // when
        statsCollector.push(metricName, values);
        Map<String, Map<MetricType, Double>> stats = statsCollector.stats();

        // then
        assertThat(stats.get(metricName).get(MetricType.MIN)).isEqualTo(-5.0);
    }

    @Test
    public void testThatMaxIsReturnedCorrectly() {
        // given
        String metricName = "4";
        double[] values = {1.65, 1.90, 1.72};

        // when
        statsCollector.push(metricName, values);
        Map<String, Map<MetricType, Double>> stats = statsCollector.stats();

        // then
        assertThat(stats.get(metricName).get(MetricType.MAX)).isEqualTo(1.90);
    }

    @Test
    public void testThatStatsAreReturnedForMultipleMetrics() {
        // given
        String metricName1 = "5";
        double[] values1 = {1.0, 2.0, 3.0};
        String metricName2 = "6";
        double[] values2 = {4.0, 5.0, 6.0};

        // when
        statsCollector.push(metricName1, values1);
        statsCollector.push(metricName2, values2);
        Map<String, Map<MetricType, Double>> stats = statsCollector.stats();

        // then
        assertThat(stats.get(metricName1).get(MetricType.AVERAGE)).isEqualTo(2.0);
        assertThat(stats.get(metricName2).get(MetricType.AVERAGE)).isEqualTo(5.0);
    }
}
