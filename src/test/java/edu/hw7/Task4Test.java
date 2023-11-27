package edu.hw7;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {
    private static final String SINGLE_THREADED_TIME_TEMPLATE = "Single-threaded time: %d ms";
    private static final String MULTI_THREADED_TIME_TEMPLATE = "Multi-threaded time: %d ms";
    private static final String AVERAGE_ACCELERATION_TEMPLATE = "Average acceleration: %.3f";
    private static final String ACCURACY_RESULT_TEMPLATE = "Accuracy for %d iterations: %.5f";
    private static final int COUNT_OF_THREADS = 4;

    private @NotNull Boolean isMultiThreadedFasterThanSingleThreaded(int iterations) {
        long startTimeSingleThreaded = System.currentTimeMillis();
        Task4.calculatePiWithSingleThread(iterations);
        long endTimeSingleThreaded = System.currentTimeMillis();
        long singleThreadedTime = endTimeSingleThreaded - startTimeSingleThreaded;

        long startTimeMultiThreaded = System.currentTimeMillis();
        Task4.calculatePiMultithreaded(iterations, COUNT_OF_THREADS);
        long endTimeMultiThreaded = System.currentTimeMillis();
        long multiThreadedTime = endTimeMultiThreaded - startTimeMultiThreaded;

        System.out.println(String.format(SINGLE_THREADED_TIME_TEMPLATE, singleThreadedTime));
        System.out.println(String.format(MULTI_THREADED_TIME_TEMPLATE, multiThreadedTime));

        double averageAcceleration = (double) singleThreadedTime / multiThreadedTime;
        System.out.println(String.format(AVERAGE_ACCELERATION_TEMPLATE, averageAcceleration));
        return startTimeMultiThreaded > multiThreadedTime;
    }

    @ParameterizedTest
    @ValueSource(ints = {10000000, 100000000, 1000000000})
    void testThatMultiThreadedFasterThanSingleThreaded(int iterations) {
        Boolean ans = isMultiThreadedFasterThanSingleThreaded(iterations);

        assertTrue(ans);
    }

    @Test
    void testThatAccuracyIsMaintained() {
        double accuracy1 = Math.abs(Math.PI - Task4.calculatePiMultithreaded(10000000, COUNT_OF_THREADS));
        double accuracy2 = Math.abs(Math.PI - Task4.calculatePiMultithreaded(100000000, COUNT_OF_THREADS));
        double accuracy3 = Math.abs(Math.PI - Task4.calculatePiMultithreaded(1000000000, COUNT_OF_THREADS));

        System.out.println(String.format(ACCURACY_RESULT_TEMPLATE, 10000000, accuracy1));
        System.out.println(String.format(ACCURACY_RESULT_TEMPLATE, 100000000, accuracy2));
        System.out.println(String.format(ACCURACY_RESULT_TEMPLATE, 1000000000, accuracy3));
    }
}
