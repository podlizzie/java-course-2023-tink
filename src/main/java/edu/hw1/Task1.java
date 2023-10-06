package edu.hw1;

public class Task1 {

    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
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
            if (seconds < 60 && seconds >= 0 && minutes >= 0) {
                return minutes * 60 + seconds;
            }
        } catch (NumberFormatException e) {
        }
        return -1;
    }
}
