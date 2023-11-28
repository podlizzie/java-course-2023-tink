package edu.project3.reports;

import edu.project3.logWorkers.LogRecord;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogReport {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String FOLDER_PATH = "src/main/resources/tablesExamples";
    private static final String FORMAT_MD = "markdown";
    private static final String FORMAT_ADOC = "adoc";
    private static final String ERROR_MSG = "Unsupported output format: %s";
    private static final String ERROR_MSG2 = "Failed to write report to file: %s";

    private LogReport() {

    }

    public static void generateReportAndWriteToFile(
        List<LogRecord> logRecords,
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        String outputFormat
    ) {
        String report = generateReport(logRecords, logPath, from, to, outputFormat);
        writeReportToFile(report, outputFormat);
    }

    private static String generateReport(
        List<LogRecord> logRecords,
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        String outputFormat
    ) {
        String averageResponseSuze = LogReportUtils.computeAverageResponseSize(logRecords);
        if (outputFormat.equals(FORMAT_MD)) {
            return
                MarkdownGenerator.generateGeneralInformation(logPath, from, to, logRecords.size(), averageResponseSuze)
                    + MarkdownGenerator.generateResourceTable(logRecords)
                    + MarkdownGenerator.generateStatusCodesTable(logRecords)
                    + MarkdownGenerator.generateAddrTable(logRecords)
                    + MarkdownGenerator.generateRequestTable(logRecords);
        } else if (outputFormat.equals(FORMAT_ADOC)) {
            return AdocGenerator.generateGeneralInformation(logPath, from, to, logRecords.size(), averageResponseSuze)
                + AdocGenerator.generateResourceTable(logRecords)
                + AdocGenerator.generateStatusCodesTable(logRecords)
                + AdocGenerator.generateAddrTable(logRecords)
                + AdocGenerator.generateRequestTable(logRecords);
        } else {
            throw new IllegalArgumentException(String.format(ERROR_MSG, outputFormat));
        }
    }

    public static void writeReportToFile(String report, String outputFormat) {
        String format = "";
        if (outputFormat.equals(FORMAT_MD)) {
            format = "md";
        } else if (outputFormat.equals(FORMAT_ADOC)) {
            format = FORMAT_ADOC;
        }

        String fileName = "example." + format;
        Path folder = Paths.get(LogReport.FOLDER_PATH);
        Path file = folder.resolve(fileName);
        try {
            Files.write(file, report.getBytes());
        } catch (Exception e) {
            LOGGER.error(String.format(ERROR_MSG2, fileName), e);
        }
    }

}
