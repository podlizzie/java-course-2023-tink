package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static String logPath;
    private static OffsetDateTime from;
    private static OffsetDateTime to;
    private static String outputFormat;

    //java -jar nginx-log-stats.jar --path src/main/java/edu/project3/logs --from 2023-08-31 --format markdown
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArgs = input.split(" ");

        parseInput(inputArgs);
        LogReader logReader = new LogReader();
        System.out.println(logReader.readLogs(logPath, from, to).collect(Collectors.toList()));
    }

    public static void parseInput(String[] args) {
        if (args.length < 5) {
            throw new IllegalArgumentException("Необходимо указать путь к NGINX лог-файлам");
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
    }

    public String getLogPath() {
        return logPath;
    }

    public OffsetDateTime getFrom() {
        return from;
    }

    public OffsetDateTime getTo() {
        return to;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

}
