package edu.project3.reports;

import edu.project3.LogRecord;
import edu.project3.StatusResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogReportUtils {
    private LogReportUtils() {

    }

    public static String computeAverageResponseSize(List<LogRecord> logRecords) {
        if (logRecords.isEmpty()) {
            throw new IllegalStateException("Log records list is empty");
        } else {
            long totalBytes = logRecords.stream()
                .mapToLong(LogRecord::getBodyBytesSent)
                .sum();
            return String.valueOf(totalBytes / logRecords.size());
        }
    }

    public static String generateResourceTable(List<LogRecord> logRecords) {
        Map<String, Long> resourceCounts = logRecords.stream()
            .collect(Collectors.groupingBy(LogRecord::getResource, Collectors.counting()));

        return resourceCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .map(entry -> "|  `" + entry.getKey() + "`  |      " + entry.getValue() + " |")
            .collect(Collectors.joining("\n"));
    }

    public static String generateStatusCodesTable(List<LogRecord> logRecords) {
        Map<Integer, Long> statusCounts = logRecords.stream()
            .collect(Collectors.groupingBy(LogRecord::getStatus, Collectors.counting()));

        return statusCounts.entrySet().stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .map(entry -> "| " + entry.getKey() + " |          " + StatusResponse.getByValue(entry.getKey())
                + "           |       " + entry.getValue() + " |")
            .collect(Collectors.joining("\n"));
    }
}
