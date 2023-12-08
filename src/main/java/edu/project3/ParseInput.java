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
    private static final String ERROR_MSG = "Must specify log file path";

    private ParseInput() {

    }

    @SuppressWarnings("ModifiedControlVariable")
    public static void parseInput(String[] args) {
        if (args.length < MIN_INPUT_LENGHT) {
            throw new IllegalArgumentException(ERROR_MSG);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("--from") && i + 1 < args.length) {
                parseFrom(args, i, formatter);
                i++;
            } else if (args[i].equals("--to") && i + 1 < args.length) {
                parseTo(args, i, formatter);
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
            outputFormat = "markdown"; //default
        }
    }

    private static void parseFrom(String[] args, int index, DateTimeFormatter formatter) {
        from = LocalDate.parse(args[index + 1], formatter)
            .atStartOfDay(ZoneOffset.UTC)
            .toOffsetDateTime();
    }

    @SuppressWarnings("MagicNumber")
    private static void parseTo(String[] args, int index, DateTimeFormatter formatter) {
        to = LocalDate.parse(args[index + 1], formatter)
            .atTime(23, 59, 59)
            .atOffset(ZoneOffset.UTC);
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
