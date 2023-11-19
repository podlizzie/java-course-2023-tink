package edu.project3.reports;

import edu.project3.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;

public class LogReport {

    public String generateReport(
        List<LogRecord> logRecords,
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        String outputFormat
    ) {
        String format;
        if (outputFormat.equals("markdown")) {
            format = "####";
        } else if (outputFormat.equals("adoc")) {
            format = "====";
        } else {
            throw new IllegalArgumentException("Unsupported output format: " + outputFormat);
        }

        return generateGeneralInformation(logPath, from, to, logRecords.size(),
            LogReportUtils.computeAverageResponseSize(logRecords), format
        ) +
            "\n" + format + " Запрашиваемые ресурсы\n\n" +
            LogReportUtils.generateResourceTable(logRecords) + "\n" +
            format + " Коды ответа\n\n" +
            LogReportUtils.generateStatusCodesTable(logRecords);
    }

    private String generateGeneralInformation(
        String logPath,
        OffsetDateTime from,
        OffsetDateTime to,
        int requestCount,
        String averageResponseSize,
        String outputFormat
    ) {
        return outputFormat + " Общая информация\n\n" +
            "|        Метрика        |     Значение |\n" +
            "|:---------------------:|-------------:|\n" +
            "|       Файл(-ы)        | `" + logPath + "` |\n" +
            "|    Начальная дата     |   " + from + " |\n" +
            "|     Конечная дата     |   " + to + " |\n" +
            "|  Количество запросов  |       " + requestCount + " |\n" +
            "| Средний размер ответа |         " + averageResponseSize + "b |\n\n";
    }
}
