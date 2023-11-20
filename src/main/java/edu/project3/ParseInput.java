package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ParseInput {
    private static String logPath;
    private static OffsetDateTime from;
    private static OffsetDateTime to;
    private static String outputFormat;
    private static final int MIN_INPUT_LENGHT = 5;

    private ParseInput() {

    }

    @SuppressWarnings({"MagicNumber", "ModifiedControlVariable"})
    public static void parseInput(String[] args) {
        if (args.length < MIN_INPUT_LENGHT) {
            throw new IllegalArgumentException("Must specify log file path");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 1; i < args.length - 1; i++) {
            if (args[i].equals("--from") && i + 1 < args.length) {
                from = LocalDate.parse(args[i + 1], formatter)
                    .atStartOfDay(ZoneOffset.UTC)
                    .toOffsetDateTime();
                i++;
            } else if (args[i].equals("--to") && i + 1 < args.length) {
                to = LocalDate.parse(args[i + 1], formatter)
                    .atTime(23, 59, 59)
                    .atOffset(ZoneOffset.UTC);
                i++;
            } else if (args[i].equals("--format") && i + 1 < args.length) {
                outputFormat = args[i + 1];
                i++;
            } else if (Objects.equals(args[i], "--path")) {
                logPath = args[i + 1];
                i++;
            }
        }
        if (outputFormat == null) {
            outputFormat = "markdown";
        }
    }

    public static String getLogPath() {
        return logPath;
    }

    public static OffsetDateTime getFrom() {
        return from;
    }

    public static OffsetDateTime getTo() {
        return to;
    }

    public static String getOutputFormat() {
        return outputFormat;
    }
}
