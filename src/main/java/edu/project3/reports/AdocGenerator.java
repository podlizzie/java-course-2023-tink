package edu.project3.reports;

import edu.project3.logWorkers.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;

public class AdocGenerator {
    private static final String FORMAT = "adoc";

    private AdocGenerator() {

    }

    @SuppressWarnings("MultipleStringLiterals")
    public static String generateGeneralInformation(
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        int requestCount,
        String averageResponseSize
    ) {
        return "==== General Information\n" + "|===\n"
            + "|Metric|Value\n"
            + "|File|" + logPath + "\n"
            + "|Start Date|" + from + "\n"
            + "|End Date|" + to + "\n"
            + "|Request Count|" + requestCount + "\n"
            + "|Average Response Size|" + averageResponseSize + " b\n" + "|===\n\n";
    }

    public static String generateResourceTable(List<LogRecord> logRecords) {
        return "==== Resource Table\n" + "|===\n"
            + "|Resource|Count\n"
            + LogReportUtils.generateResourceTable(logRecords, FORMAT) + "\n|===\n\n";
    }

    public static String generateStatusCodesTable(List<LogRecord> logRecords) {
        return "==== Status Codes Table\n" + "|===\n"
            + "|Status Code|Description|Count\n"
            + LogReportUtils.generateStatusCodesTable(logRecords, FORMAT) + "\n|===";
    }
}
