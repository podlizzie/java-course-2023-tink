package edu.hw7;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final int RADIUS = 1;
    private static final double FOUR_NUM = 4.0;
    private final static String ERROR_MSG = "Error while join threads %s";

    private Task4() {

    }

    public static void calculatePiWithSingleThread(int iterations) {
        int circleCount = 0;
        for (int i = 0; i < iterations; i++) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= RADIUS) {
                circleCount++;
            }
        }
    }

    public static double calculatePiMultithreaded(int iterations, int numThreads) {
        AtomicLong circleCount = new AtomicLong(0);

        List<Thread> threads = Stream.generate(() ->
                new Thread(() -> {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    for (int j = 0; j < iterations / numThreads; j++) {
                        double x = random.nextDouble();
                        double y = random.nextDouble();
                        if (x * x + y * y <= RADIUS) {
                            circleCount.incrementAndGet();
                        }
                    }
                }))
            .limit(numThreads)
            .peek(Thread::start)
            .toList();

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.info(String.format(ERROR_MSG, e.getMessage()));
            }
        });

        return FOUR_NUM * circleCount.get() / iterations;
    }
}
