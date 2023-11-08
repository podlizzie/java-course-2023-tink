package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.jetbrains.annotations.NotNull;

public class Task1 {
    private static final String REGEX_PATTERN = "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - "
        + "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}";

    private Task1() {

    }

    /**
     * Calculates the average length of sessions.
     *
     * @param input an array of rows containing records of the beginning and end of each session.
     * @return Duration, representing the average length of sessions.
     * @throws IllegalArgumentException if the input array is empty or contains incorrect entries.
     */
    public static Duration calculateSessionTime(String @NotNull [] input) {
        for (String line : input) {
            if (!isValidInput(line)) {
                throw new IllegalArgumentException("Illegal line");
            }
        }
        if (input.length == 0) {
            throw new IllegalArgumentException("Input array is empty");
        }

        long totalSeconds = 0;

        for (String entry : input) {
            String[] parts = entry.split(" - ");

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(parts[0], pattern);
            LocalDateTime endTime = LocalDateTime.parse(parts[1], pattern);

            totalSeconds += Duration.between(startTime, endTime).getSeconds();
        }

        return totalSeconds > 0 ? Duration.ofSeconds(totalSeconds / input.length) : Duration.ZERO;
    }

    private static boolean isValidInput(String line) {
        return line.matches(REGEX_PATTERN);
    }
}
