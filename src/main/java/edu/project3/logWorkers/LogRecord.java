package edu.project3.logWorkers;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRecord {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(.*) - (.*) \\[(.*)] \"(\\w+) (/.+\\b) .*\" (\\d{3}) (\\d+) \"(.+)\" \"(.*)\"");

    private static final String ERROR_MSG = "Invalid log format: %s";
    private OffsetDateTime timestamp;
    private int status;
    private long bodyBytesSent;
    private String resource;
    private String remoteAddr;
    private String request;

    @SuppressWarnings("MagicNumber")
    public void logParser(String log) {
        Matcher matcher = LOG_PATTERN.matcher(log);
        if (matcher.matches()) {
            remoteAddr = matcher.group(1);
            timestamp = OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER);
            request = matcher.group(4);
            resource = matcher.group(5);
            status = Integer.parseInt(matcher.group(6));
            bodyBytesSent = Long.parseLong(matcher.group(7));
        } else {
            throw new IllegalArgumentException(String.format(ERROR_MSG, log));
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

    public String getRequest() {
        return request;
    }
}
