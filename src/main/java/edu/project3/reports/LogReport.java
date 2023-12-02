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
    private final MarkdownGenerator markdownGenerator = new MarkdownGenerator();
    private final AdocGenerator adocGenerator = new AdocGenerator();

    public void generateReportAndWriteToFile(
        List<LogRecord> logRecords,
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        String outputFormat
    ) {
        String report = generateReport(logRecords, outputFormat, logPath, from, to);
        writeReportToFile(report, outputFormat);
    }

    private String generateReport(
        List<LogRecord> logRecords,
        String outputFormat,
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to
    ) {
        String averageResponseSize = LogReportUtils.computeAverageResponseSize(logRecords);
        if (outputFormat.equals(FORMAT_MD)) {
            return generateMarkdown(logRecords, logPath, from, to, averageResponseSize);
        } else if (outputFormat.equals(FORMAT_ADOC)) {
            return generateAdoc(logRecords, logPath, from, to, averageResponseSize);
        } else {
            throw new IllegalArgumentException(String.format(ERROR_MSG, outputFormat));
        }
    }

    private String generateAdoc(
        List<LogRecord> logRecords,
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        String averageResponseSize
    ) {
        return adocGenerator.generateGeneralInformation(logPath, from, to, logRecords.size(), averageResponseSize)
            + adocGenerator.generateResourceTable(logRecords)
            + adocGenerator.generateStatusCodesTable(logRecords)
            + adocGenerator.generateAddrTable(logRecords)
            + adocGenerator.generateRequestTable(logRecords);
    }

    private String generateMarkdown(
        List<LogRecord> logRecords,
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        String averageResponseSize
    ) {
        return markdownGenerator.generateGeneralInformation(logPath, from, to, logRecords.size(), averageResponseSize)
            + markdownGenerator.generateResourceTable(logRecords)
            + markdownGenerator.generateStatusCodesTable(logRecords)
            + markdownGenerator.generateAddrTable(logRecords)
            + markdownGenerator.generateRequestTable(logRecords);
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
