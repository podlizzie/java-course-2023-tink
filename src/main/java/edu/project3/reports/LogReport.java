package edu.project3.reports;

import edu.project3.LogRecord;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogReport {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String FOLDER_PATH = "src/main/java/edu/project3/tables_examples";

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
        if (outputFormat.equals("markdown")) {
            return
                MarkdownGenerator.generateGeneralInformation(logPath, from, to, logRecords.size(), averageResponseSuze)
                    + MarkdownGenerator.generateResourceTable(logRecords)
                    + MarkdownGenerator.generateStatusCodesTable(logRecords);
        } else if (outputFormat.equals("adoc")) {
            return AdocGenerator.generateGeneralInformation(logPath, from, to, logRecords.size(), averageResponseSuze)
                + AdocGenerator.generateResourceTable(logRecords)
                + AdocGenerator.generateStatusCodesTable(logRecords);
        } else {
            throw new IllegalArgumentException("Unsupported output format: " + outputFormat);
        }
    }

    private static void writeReportToFile(String report, String outputFormat) {
        if (outputFormat.equals("markdown")) {
            outputFormat = "md";
        }

        String fileName = "example." + outputFormat;
        Path folder = Paths.get(LogReport.FOLDER_PATH);
        Path file = folder.resolve(fileName);
        try {
            Files.write(file, report.getBytes());
        } catch (Exception e) {
            LOGGER.error("Failed to write report to file: " + fileName, e);
        }
    }

}
