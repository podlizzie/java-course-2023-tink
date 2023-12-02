package edu.project3;

import edu.project3.logWorkers.LogRecord;
import edu.project3.reports.MarkdownGenerator;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownGeneratorTest {
    private static final List<LogRecord> sampleLogRecords = createSampleLogRecords();
    private static final MarkdownGenerator markdownGenerator = new MarkdownGenerator();

    @Test
    void testThatGenerateGeneralInformationReturnedCorrectValue() {
        String generalInformation = markdownGenerator.generateGeneralInformation("logPath", null, null, 100, "1234");
        assertThat(generalInformation).isEqualTo("""
            #### General Information

            |Metric|Value|
            |:---------------------:|-------------:|
            |File(s)|logPath|
            |Start Date|null|
            |End Date|null|
            |Request Count|100|
            |Average Response Size|1234 b|

            """);
    }

    @Test
    void testThatGenerateResourceTableReturnedCorrectTable() {
        String resourceTable = markdownGenerator.generateResourceTable(sampleLogRecords);
        assertThat(resourceTable).isEqualTo("""
            #### Resource Table

            |Resource|Count|
            |:---------------------:|-------------:|
            |/downloads/product_1|2|

            """);
    }

    @Test
    void testThatGenerateStatusCodesTableReturnedCorrectTable() {
        String statusCodesTable = markdownGenerator.generateStatusCodesTable(sampleLogRecords);
        assertThat(statusCodesTable).isEqualTo("""
            #### Status Codes Table

            |Status Code|Description|Count|
            |:---------------------:|------------:|-----------:|
            |304|NOT_MODIFIED|1|
            |200|OK|1|

            """);
    }

    @Test
    void testThatGenerateAddrTableReturnedCorrectTable() {
        String addrTable = markdownGenerator.generateAddrTable(sampleLogRecords);
        assertThat(addrTable).isEqualTo("""
            #### IP Address Table

            |IP|Count|
            |:---------------------:|-------------:|
            |93.180.71.3|1|
            |93.180.71.4|1|

            """);
    }

    @Test
    void testThatGenerateRequestTableReturnedCorrectTable() {
        String requestTable = markdownGenerator.generateRequestTable(sampleLogRecords);
        assertThat(requestTable).isEqualTo("""
            #### Requests Table

            |Request|Count|
            |:---------------------:|-------------:|
            |GET|2|

            """);
    }

    private static @NotNull List<LogRecord> createSampleLogRecords() {
        List<LogRecord> sampleLogs = new ArrayList<>();

        LogRecord log1 = new LogRecord();
        log1.logParser(
            "93.180.71.3 - - [17/May/2015:08:05:27 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"");
        sampleLogs.add(log1);

        LogRecord log2 = new LogRecord();
        log2.logParser(
            "93.180.71.4 - - [17/May/2015:08:05:27 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 6 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"");
        sampleLogs.add(log2);

        return sampleLogs;
    }
}
