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
    private static final int ITERATIONS10M = 10000000;
    private static final int ITERATIONS100M = 100000000;
    private static final int ITERATIONS1B = 1000000000;

    @Test
    void testThatAccuracyImprovesWithMoreIterations() {
        // Given
        // When
        double accuracy1 = Math.abs(Math.PI - Task4.calculatePiMultithreaded(ITERATIONS10M, COUNT_OF_THREADS));
        double accuracy2 = Math.abs(Math.PI - Task4.calculatePiMultithreaded(ITERATIONS100M, COUNT_OF_THREADS));
        double accuracy3 = Math.abs(Math.PI - Task4.calculatePiMultithreaded(ITERATIONS1B, COUNT_OF_THREADS));

        // Then
        System.out.printf((ACCURACY_RESULT_TEMPLATE) + "%n", ITERATIONS10M, accuracy1);
        System.out.printf((ACCURACY_RESULT_TEMPLATE) + "%n", ITERATIONS100M, accuracy2);
        System.out.printf((ACCURACY_RESULT_TEMPLATE) + "%n", ITERATIONS1B, accuracy3);

        assertTrue(accuracy2 < accuracy1);
    }

    private @NotNull Boolean isMultiThreadedFasterThanSingleThreaded(int iterations) {
        long startTimeSingleThreaded = System.currentTimeMillis();
        Task4.calculatePiWithSingleThread(iterations);
        long endTimeSingleThreaded = System.currentTimeMillis();
        long singleThreadedTime = endTimeSingleThreaded - startTimeSingleThreaded;

        long startTimeMultiThreaded = System.currentTimeMillis();
        Task4.calculatePiMultithreaded(iterations, COUNT_OF_THREADS);
        long endTimeMultiThreaded = System.currentTimeMillis();
        long multiThreadedTime = endTimeMultiThreaded - startTimeMultiThreaded;

        System.out.printf((SINGLE_THREADED_TIME_TEMPLATE) + "%n", singleThreadedTime);
        System.out.printf((MULTI_THREADED_TIME_TEMPLATE) + "%n", multiThreadedTime);

        double averageAcceleration = (double) singleThreadedTime / multiThreadedTime;
        System.out.printf((AVERAGE_ACCELERATION_TEMPLATE) + "%n", averageAcceleration);

        return startTimeMultiThreaded > multiThreadedTime;
    }

    @ParameterizedTest
    @ValueSource(ints = {ITERATIONS10M, ITERATIONS100M, ITERATIONS1B})
    void testThatMultiThreadedFasterThanSingleThreaded(int iterations) {
        // Given
        // When
        Boolean ans = isMultiThreadedFasterThanSingleThreaded(iterations);

        //Then
        assertTrue(ans);
    }
}
