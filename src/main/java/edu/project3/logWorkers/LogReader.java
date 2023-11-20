package edu.project3.logWorkers;

import java.io.File;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class LogReader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @SuppressWarnings("MagicNumber")
    public Stream<LogRecord> readLogs(@NotNull String logPath, OffsetDateTime from, OffsetDateTime to) {
        try {
            Stream<String> logLines = null;
            if (logPath.startsWith("https://") || logPath.startsWith("http://")) {
                HttpResponse<String> response = sendHttpRequest(logPath);
                logLines = Arrays.stream(response.body().split("\\r?\\n"));
            } else {
                if (logPath.contains("**")) {
                    int index = logPath.indexOf("**");
                    File folder = new File(logPath.substring(0, index));
                    String fileName = logPath.substring(index + 3);
                    File[] files = listFilesForFolder(folder, fileName);
                    logLines = Arrays.stream(files)
                        .filter(File::isFile)
                        .map(File::toPath)
                        .flatMap(this::readLines);

                } else if (logPath.contains("*")) {
                    File folder = new File(logPath.replace("*", ""));
                    File[] files = folder.listFiles();
                    if (files != null) {
                        logLines = Arrays.stream(files)
                            .filter(File::isFile)
                            .map(File::toPath)
                            .flatMap(this::readLines);
                    }
                } else {
                    Path logsPath = Paths.get(logPath);
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

    private static HttpResponse<String> sendHttpRequest(String url)
        throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET()
            .build();

        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private File[] listFilesForFolder(File folder, String fileName) {
        List<File> fileList = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                fileList.addAll(Arrays.asList(listFilesForFolder(fileEntry, fileName)));
            } else {
                if (fileEntry.getName().equals(fileName)) {
                    fileList.add(fileEntry);
                }
            }
        }
        return fileList.toArray(new File[fileList.size()]);
    }

}
