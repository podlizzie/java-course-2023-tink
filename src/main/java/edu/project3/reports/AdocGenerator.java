package edu.project3.reports;

import edu.project3.logWorkers.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;

public class AdocGenerator {
    private static final String FORMAT = "adoc";
    private static final String SECTION_HEADER_START = "==== ";
    private static final String SECTION_HEADER_END = "\n|===\n";
    private static final String TABLE_ROW_SEPARATOR = "\n";
    private static final String TABLE_COLUMN_SEPARATOR = "|";
    private static final String COUNT_ROW = "Count";

    private AdocGenerator() {

    }

    public static String generateGeneralInformation(
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        int requestCount,
        String averageResponseSize
    ) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("General Information")
            .append(SECTION_HEADER_END)
            .append(TABLE_COLUMN_SEPARATOR).append("Metric").append(TABLE_COLUMN_SEPARATOR).append("Value")
            .append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("File(s)").append(TABLE_COLUMN_SEPARATOR).append(logPath)
            .append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("Start Date").append(TABLE_COLUMN_SEPARATOR).append(from)
            .append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("End Date").append(TABLE_COLUMN_SEPARATOR).append(to)
            .append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("Request Count").append(TABLE_COLUMN_SEPARATOR).append(requestCount)
            .append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("Average Response Size").append(TABLE_COLUMN_SEPARATOR)
            .append(averageResponseSize).append(" b")
            .append(TABLE_ROW_SEPARATOR)
            .append(SECTION_HEADER_END);

        return stringBuilder.toString();
    }

    public static String generateResourceTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("Resource Table")
            .append(SECTION_HEADER_END)
            .append(TABLE_COLUMN_SEPARATOR).append("Resourse")
            .append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW).append(TABLE_ROW_SEPARATOR)
            .append(LogReportUtils.generateResourceTable(logRecords, FORMAT)).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_HEADER_END);
        return stringBuilder.toString();
    }

    public static String generateStatusCodesTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("Status Codes Table")
            .append(SECTION_HEADER_END)
            .append(TABLE_COLUMN_SEPARATOR).append("Status Code")
            .append(TABLE_COLUMN_SEPARATOR).append("Description")
            .append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW).append(TABLE_ROW_SEPARATOR)
            .append(LogReportUtils.generateStatusCodesTable(logRecords, FORMAT)).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_HEADER_END);
        return stringBuilder.toString();
    }

    public static String generateAddrTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("IP Address Table")
            .append(SECTION_HEADER_END)
            .append(TABLE_COLUMN_SEPARATOR).append("IP")
            .append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW).append(TABLE_ROW_SEPARATOR)
            .append(LogReportUtils.generateTopIpAddressesTable(logRecords, FORMAT)).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_HEADER_END);
        return stringBuilder.toString();
    }

    public static String generateRequestTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("Requests Table")
            .append(SECTION_HEADER_END)
            .append(TABLE_COLUMN_SEPARATOR).append("Request")
            .append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW).append(TABLE_ROW_SEPARATOR)
            .append(LogReportUtils.generateRequestTable(logRecords, FORMAT)).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_HEADER_END);
        return stringBuilder.toString();
    }
}
