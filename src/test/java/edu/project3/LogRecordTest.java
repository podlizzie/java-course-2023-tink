package edu.project3;

import edu.project3.logWorkers.LogRecord;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogRecordTest {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final String LOG =
        "93.180.71.3 - - [17/May/2015:08:05:27 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";


    @Test
    void testThatLogParserParsesLogCorrectly() {
        LogRecord logRecord = new LogRecord();

        //when
        logRecord.logParser(LOG);

        //then
        assertThat(logRecord.getRemoteAddr()).isEqualTo("93.180.71.3");
        assertThat(logRecord.getRequest()).isEqualTo("GET");
        assertThat(logRecord.getResource()).isEqualTo("/downloads/product_1");
        assertThat(logRecord.getStatus()).isEqualTo(304);
        assertThat(logRecord.getBodyBytesSent()).isEqualTo(0);
        OffsetDateTime expectedTimestamp =
            OffsetDateTime.parse("17/May/2015:08:05:27 +0000", DATE_TIME_FORMATTER.withLocale(Locale.ENGLISH));
        assertThat(logRecord.getTimestamp()).isEqualTo(expectedTimestamp);
    }

    @Test
    void testThatLogParserReturnedExceptionForInvalidLogFormat() {
        LogRecord logRecord = new LogRecord();
        String invalidLog = "Invalid log entry";
        assertThrows(IllegalArgumentException.class, () -> logRecord.logParser(invalidLog));
    }

}
