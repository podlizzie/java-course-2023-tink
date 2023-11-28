package edu.project3.reports;

import edu.project3.logWorkers.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;

public class MarkdownGenerator {
    private static final String FORMAT = "markdown";
    private static final String SECTION_HEADER_START = "#### ";
    private static final String SECTION_TABLE = "|:---------------------:|-------------:|\n";
    private static final String SECTION_TABLE2 = "|:---------------------:|------------:|-----------:|\n";
    private static final String TABLE_ROW_SEPARATOR = "\n";
    private static final String TABLE_COLUMN_SEPARATOR = "|";
    private static final String COUNT_ROW = "Count";

    private MarkdownGenerator() {

    }

    public static String generateGeneralInformation(
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        int requestCount,
        String averageResponseSize
    ) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("General Information\n\n")
            .append(TABLE_COLUMN_SEPARATOR).append("Metric").append(TABLE_COLUMN_SEPARATOR)
            .append("Value").append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR).append(SECTION_TABLE)
            .append(TABLE_COLUMN_SEPARATOR).append("File(s)").append(TABLE_COLUMN_SEPARATOR)
            .append(logPath).append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("Start Date").append(TABLE_COLUMN_SEPARATOR)
            .append(from).append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("End Date").append(TABLE_COLUMN_SEPARATOR)
            .append(to).append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("Request Count").append(TABLE_COLUMN_SEPARATOR)
            .append(requestCount).append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(TABLE_COLUMN_SEPARATOR).append("Average Response Size").append(TABLE_COLUMN_SEPARATOR)
            .append(averageResponseSize).append(" b").append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(TABLE_ROW_SEPARATOR);

        return stringBuilder.toString();
    }

    public static String generateResourceTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("Resource Table\n\n")
            .append(TABLE_COLUMN_SEPARATOR).append("Resource").append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW)
            .append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_TABLE).append(LogReportUtils.generateResourceTable(logRecords, FORMAT))
            .append(TABLE_ROW_SEPARATOR).append(TABLE_ROW_SEPARATOR);
        return stringBuilder.toString();
    }

    public static String generateStatusCodesTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("Status Codes Table\n\n")
            .append(TABLE_COLUMN_SEPARATOR).append("Status Code").append(TABLE_COLUMN_SEPARATOR).append("Description")
            .append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW)
            .append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_TABLE2).append(LogReportUtils.generateStatusCodesTable(logRecords, FORMAT))
            .append(TABLE_ROW_SEPARATOR).append(TABLE_ROW_SEPARATOR);
        return stringBuilder.toString();
    }

    public static String generateAddrTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("IP Address Table\n\n")
            .append(TABLE_COLUMN_SEPARATOR).append("IP").append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW)
            .append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_TABLE).append(LogReportUtils.generateTopIpAddressesTable(logRecords, FORMAT))
            .append(TABLE_ROW_SEPARATOR).append(TABLE_ROW_SEPARATOR);
        return stringBuilder.toString();
    }

    public static String generateRequestTable(List<LogRecord> logRecords) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SECTION_HEADER_START).append("Requests Table\n\n")
            .append(TABLE_COLUMN_SEPARATOR).append("Request").append(TABLE_COLUMN_SEPARATOR).append(COUNT_ROW)
            .append(TABLE_COLUMN_SEPARATOR).append(TABLE_ROW_SEPARATOR)
            .append(SECTION_TABLE).append(LogReportUtils.generateRequestTable(logRecords, FORMAT))
            .append(TABLE_ROW_SEPARATOR).append(TABLE_ROW_SEPARATOR);
        return stringBuilder.toString();
    }
}
