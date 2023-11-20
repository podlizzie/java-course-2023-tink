package edu.project3;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParseInputTest {
    @Test
    void testThatParseInputParsesLogPathCorrectly() {
        String[] args = {"--path", "example.log", "--from", "2022-01-01", "--to", "2022-01-31"};
        ParseInput.parseInput(args);
        assertThat(ParseInput.getLogPath()).isEqualTo("example.log");
    }

    @Test
    void testThatParseInputParsesFromAndToDatesCorrectly() {
        String[] args = {"--path", "example.log", "--from", "2022-01-01", "--to", "2022-01-31"};
        ParseInput.parseInput(args);
        OffsetDateTime expectedFrom = OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime expectedTo = OffsetDateTime.of(2022, 1, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        assertThat(ParseInput.getFrom()).isEqualTo(expectedFrom);
        assertThat(ParseInput.getTo()).isEqualTo(expectedTo);
    }

    @Test
    void testThatParseInputReturnedCorrectDefaultFormat() {
        String[] args1 = {"--path", "example.log", "--from", "2022-01-01", "--to", "2022-01-31"};
        ParseInput.parseInput(args1);
        assertThat(ParseInput.getOutputFormat()).isEqualTo("markdown");
    }

    @Test
    void testThatParseInputReturnedExceptionForMissingLogPath() {
        String[] args = {"--from"};
        assertThatThrownBy(() -> ParseInput.parseInput(args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Must specify log file path");
    }
}
