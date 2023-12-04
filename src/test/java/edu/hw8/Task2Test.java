package edu.hw8;

import edu.hw8.task2.CalculatorFibonacci;
import edu.hw8.task2.FixedThreadPool;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final int THREAD_POOL_SIZE = 4;
    private static final int MAX_N = 40;

    @Test
    public void assertThatThreadPoolWorksFasterThanSingleThread() {

        //given
        //when
        long singleThreadStartTime = System.nanoTime();
        for (int i = 0; i < MAX_N; i++) {
            CalculatorFibonacci.calculateFibonacci(i);
        }
        long singleThreadEndTime = System.nanoTime();

        long threadPoolStartTime = System.nanoTime();
        try (FixedThreadPool pool = FixedThreadPool.create(THREAD_POOL_SIZE)) {
            for (int i = 0; i < MAX_N; i++) {
                final int finalI = i;
                pool.execute(() -> LOGGER.info(
                    "For n = {}: {}",
                    finalI,
                    CalculatorFibonacci.calculateFibonacci(finalI)
                ));
            }
        }
        long threadPoolEndTime = System.nanoTime();

        //then
        assertTrue(threadPoolEndTime - threadPoolStartTime < singleThreadEndTime - singleThreadStartTime);
    }

    @Test
    void testThatThreadPoolExecutesAllTasks() {
        // given
        int numberOfThreads = 10;
        AtomicInteger countOfExecutedTasks = new AtomicInteger(0);
        int numberOfTasks = 15;

        // when
        try (FixedThreadPool pool = new FixedThreadPool(numberOfThreads)) {
            for (int i = 0; i < numberOfTasks; i++) {
                pool.execute(countOfExecutedTasks::incrementAndGet);
            }
            pool.start();
        }

        // then
        assertEquals(numberOfTasks, countOfExecutedTasks.get());
    }
}
