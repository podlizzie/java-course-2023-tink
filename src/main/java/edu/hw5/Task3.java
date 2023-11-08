package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Task3 {
    private static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    private static final String DATE_FORMAT_2 = "M/d/yyyy";
    private static final String DATE_FORMAT_3 = "M/d/yy";
    private static final int LENGHT_OF_FORMAT_X_DAYS_AGO = 3;

    private Task3() {

    }

    public static Optional<LocalDate> parseDate(String string) {
        // Attempt to parse the string as "yyyy-MM-dd" format
        Optional<LocalDate> date = parseWithFormat(string, DATE_FORMAT_1);

        // Attempt to parse the string as "M/d/yyyy" format
        if (date.isEmpty()) {
            date = parseWithFormat(string, DATE_FORMAT_2);
        }

        // Attempt to parse the string as "M/d/yy" format
        if (date.isEmpty()) {
            date = parseWithFormat(string, DATE_FORMAT_3);
        }

        // Other formats
        if (date.isEmpty()) {
            String[] parts = string.split(" ");
            if (parts.length == LENGHT_OF_FORMAT_X_DAYS_AGO && (parts[1].equalsIgnoreCase("days")
                || parts[1].equalsIgnoreCase("day")) && isNumeric(parts[0])) {
                int days = Integer.parseInt(parts[0]);
                date = Optional.of(LocalDate.now().minusDays(days));
            } else {
                switch (string.toLowerCase()) {
                    case "tomorrow":
                        date = Optional.of(LocalDate.now().plusDays(1));
                        break;
                    case "today":
                        date = Optional.of(LocalDate.now());
                        break;
                    case "yesterday":
                        date = Optional.of(LocalDate.now().minusDays(1));
                        break;
                    default:
                        break;
                }
            }
        }

        return date;
    }

    private static Optional<LocalDate> parseWithFormat(String string, String format) {
        try {
            LocalDate date = LocalDate.parse(string, DateTimeFormatter.ofPattern(format));
            return Optional.of(date);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
