package edu.project3;

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
        "^(.*) - (.*) \\[(.*)] \"(\\w+) (.*)\" (\\d{3}) (\\d+) \"(.+)\" \"(.*)\"");

    private String remoteAddr;
    private String remoteUser;
    private OffsetDateTime timestamp;
    private String request;
    private int status;
    private String statusResponse;
    private long bodyBytesSent;
    private String httpReferer;
    private String resource;
    private String httpUserAgent;

    //93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"

    public void logParser(String log) {
        Matcher matcher = LOG_PATTERN.matcher(log);
        if (matcher.matches()) {
            remoteAddr = matcher.group(1);
            remoteUser = matcher.group(2);
            timestamp = OffsetDateTime.parse(matcher.group(3), DATE_TIME_FORMATTER);
            request = matcher.group(4);
            resource = matcher.group(5);
            status = Integer.parseInt(matcher.group(6));
            statusResponse = String.valueOf(StatusResponse.getByValue(status));
            bodyBytesSent = Long.parseLong(matcher.group(7));
            httpReferer = matcher.group(8);
            httpUserAgent = matcher.group(9);
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

    public String getRequest() {
        return request;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public String getResource() {
        return resource;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }
}
