package edu.project3;

import edu.project3.logWorkers.LogReader;
import edu.project3.logWorkers.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LogReaderTest {
    private static final String DIR_PATH = "src/test/java/edu/project3/logs/test2*";
    LogReader logReader = new LogReader();

    @Test
    void testThatReadLogsReturnsExpectedNumberOfLogs() {
        OffsetDateTime from = OffsetDateTime.parse("2015-05-17T00:00:00Z");
        OffsetDateTime to = OffsetDateTime.parse("2015-05-17T23:59:40Z");

        List<LogRecord> logs = logReader.readLogs(DIR_PATH, from, to).collect(Collectors.toList());

        assertThat(logs).hasSize(4);
    }

    @Test
    void testThatReadLogsReturnsLogsInRange() {
        OffsetDateTime from = OffsetDateTime.parse("2023-01-01T00:00:00Z");
        OffsetDateTime to = OffsetDateTime.parse("2023-12-31T23:59:59Z");

        List<LogRecord> logs = logReader.readLogs(DIR_PATH, from, to).collect(Collectors.toList());

        assertThat(logs).allMatch(log -> log.getTimestamp().isAfter(from) && log.getTimestamp().isBefore(to));
    }

    @Test
    void testThatReadLogsReturnsAllLogsIfFromAndToNull() {
        List<LogRecord> logs = logReader.readLogs(DIR_PATH, null, null).collect(Collectors.toList());

        assertThat(logs).hasSize(5);
    }

    @Test
    void testThatGivenLogPathWithWildcardReturnedCorrectlyLenghtOfLogLines() {
        List<LogRecord> logs = logReader.readLogs("src/test/java/edu/project3/logs/test1*",
            null, null
        ).toList();

        assertThat(logs).hasSize(4);
    }

    @Test
    void testThatGivenLogPathWithHttpReturnedCorrectlyLenghtOfLogLines() {
        List<LogRecord> logs = logReader.readLogs(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            null,
            null
        ).toList();

        assertThat(logs).hasSize(51462);
    }

    @Test
    void testThatGivenNestedLogPathReturnedCorrectlyLenghtOfLogLines() {
        List<LogRecord> logs = logReader.readLogs("src/test/java/edu/project3/logs/**/2023-08-31.txt",
            null, null
        ).collect(Collectors.toList());

        assertThat(logs).hasSize(7);
    }
}
