package edu.project3.logWorkers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogRecord {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(.*) - (.*) \\[(.*)] \"(\\w+) (/.+\\b) .*\" (\\d{3}) (\\d+) \"(.+)\" \"(.*)\"");

    private OffsetDateTime timestamp;
    private int status;
    private long bodyBytesSent;
    private String resource;
    private String remoteAddr;

    @SuppressWarnings("MagicNumber")
    public void logParser(String log) {
        Matcher matcher = LOG_PATTERN.matcher(log);
        if (matcher.matches()) {
            remoteAddr = matcher.group(1);
            String remoteUser = matcher.group(2);
            timestamp = OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER);
            String request = matcher.group(4);
            resource = matcher.group(5);
            status = Integer.parseInt(matcher.group(6));
            String statusResponse = String.valueOf(StatusResponse.getByValue(status));
            bodyBytesSent = Long.parseLong(matcher.group(7));
            String httpReferer = matcher.group(8);
            String httpUserAgent = matcher.group(9);
        } else {
            LOGGER.info("Error: Invalid log format - Unable to parse the log entry: " + log);
        }
    }

    public long getBodyBytesSent() {
        return bodyBytesSent;
    }

    public int getStatus() {
        return status;
    }

    public String getResource() {
        return resource;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }
}
