package edu.project3.reports;

import edu.project3.logWorkers.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;

public abstract class LogReportGenerator {
    public abstract String generateGeneralInformation(
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        int requestCount,
        String averageResponseSize
    );

    public abstract String generateResourceTable(List<LogRecord> logRecords);

    public abstract String generateStatusCodesTable(List<LogRecord> logRecords);

    public abstract String generateAddrTable(List<LogRecord> logRecords);

    public abstract String generateRequestTable(List<LogRecord> logRecords);
}
