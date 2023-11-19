package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class LogReader {
    private final static Logger LOGGER = LogManager.getLogger();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public Stream<LogRecord> readLogs(@NotNull String logPath, OffsetDateTime from, OffsetDateTime to) {
        try {
            Stream<String> logLines;
            if (logPath.startsWith("https://") || logPath.startsWith("http://")) {
                HttpResponse<String> response = sendHttpRequest(logPath);
                logLines = Arrays.stream(response.body().split("\\r?\\n"));
            } else {
                Path logsPath = Paths.get(logPath);
                if (Files.isDirectory(logsPath)) {
                    logLines = Files.find(logsPath, Integer.MAX_VALUE, (path, basicFileAttributes) ->
                            basicFileAttributes.isRegularFile())
                        .flatMap(this::readLines);

                } else {
                    logLines = Files.lines(logsPath);
                }
            }

            return logLines.map(this::parseLogRecord)
                .filter(log -> from == null || log.getTimestamp().isAfter(from))
                .filter(log -> to == null || log.getTimestamp().isBefore(to));

        } catch (IOException | URISyntaxException | InterruptedException e) {
            LOGGER.info(e);
            return Stream.empty();
        }
    }

    private Stream<String> readLines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            LOGGER.info(e);
            return Stream.empty();
        }
    }

    private LogRecord parseLogRecord(String log) {
        LogRecord logRecord = new LogRecord();
        logRecord.logParser(log);
        return logRecord;
    }

    private HttpResponse<String> sendHttpRequest(String url)
        throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET()
            .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
