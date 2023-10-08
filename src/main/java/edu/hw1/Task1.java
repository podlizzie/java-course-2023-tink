package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {

    private static final int SECONDS_IN_MUNUTE = 60;
    private final static Logger LOGGER = LogManager.getLogger();

    private Task1() {
    }

    public static long minutesToSeconds(String time) {
        if (time == null || time.isEmpty()) {
            return -1;
        }
        String[] parts = time.replace(" ", "").split(":");
        if (parts.length != 2) {
            return -1;
        }
        try {
            long minutes = Long.parseLong(parts[0]);
            long seconds = Long.parseLong(parts[1]);
            if (seconds < SECONDS_IN_MUNUTE && seconds >= 0 && minutes >= 0) {
                return minutes * SECONDS_IN_MUNUTE + seconds;
            }
        } catch (NumberFormatException e) {
            LOGGER.info(e);
        }
        return -1;
    }
}
