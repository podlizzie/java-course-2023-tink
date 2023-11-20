package edu.project3.reports;

import edu.project3.logWorkers.LogRecord;
import edu.project3.logWorkers.StatusResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogReportUtils {
    private static final String FORMAT_MD = "markdown";

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

    public static String generateResourceTable(List<LogRecord> logRecords, String format) {
        Map<String, Long> resourceCounts = logRecords.stream()
            .collect(Collectors.groupingBy(LogRecord::getResource, Collectors.counting()));

        return resourceCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .map(entry -> {
                String count = entry.getValue().toString();
                return format.equals(FORMAT_MD)
                    ? "|" + entry.getKey() + "|" + count + "|"
                    : "|" + entry.getKey() + "| " + count;
            })
            .collect(Collectors.joining("\n"));
    }

    public static String generateStatusCodesTable(List<LogRecord> logRecords, String format) {
        Map<Integer, Long> statusCounts = logRecords.stream()
            .collect(Collectors.groupingBy(LogRecord::getStatus, Collectors.counting()));

        return statusCounts.entrySet().stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .map(entry -> {
                String statusCode = entry.getKey().toString();
                String statusDescription = String.valueOf(StatusResponse.getByValue(entry.getKey()));
                String count = entry.getValue().toString();
                return format.equals(FORMAT_MD)
                    ? "|" + statusCode + "|" + statusDescription + "|" + count + "|"
                    : "|" + statusCode + "|" + statusDescription + "|" + count;
            })
            .collect(Collectors.joining("\n"));
    }

    public static String generateIpAddressesTable(List<LogRecord> logRecords, String format) {
        Map<String, Long> ipAddressCounts = logRecords.stream()
            .collect(Collectors.groupingBy(LogRecord::getRemoteAddr, Collectors.counting()));

        return ipAddressCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .map(entry -> {
                String count = entry.getValue().toString();
                return format.equals(FORMAT_MD)
                    ? "|" + entry.getKey() + "|" + count + "|"
                    : "|" + entry.getKey() + "|" + count;
            })
            .collect(Collectors.joining("\n"));
    }
}
