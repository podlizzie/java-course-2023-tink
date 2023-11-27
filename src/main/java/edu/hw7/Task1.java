package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {
    private Task1() {

    }

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_MSG = "Error while join threads %s";
    private static final int INITIAL_VALUE = 0;
    private static final AtomicInteger count = new AtomicInteger(INITIAL_VALUE);

    public static int getCount() {
        return count.get();
    }

    private static void incrementCount() {
        count.incrementAndGet();
    }

    public static void runIncrement() {
        Thread thread1 = new Thread(() -> {
            incrementCount();
            LOGGER.info(String.format("Thread 1. Current value: %s", getCount()));
        });

        Thread thread2 = new Thread(() -> {
            incrementCount();
            LOGGER.info(String.format("Thread 2. Current value: %s", getCount()));
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            LOGGER.info(String.format(ERROR_MSG, e.getMessage()));
        }
    }
}
