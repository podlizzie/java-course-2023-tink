package edu.project3;

import edu.project3.logWorkers.LogRecord;
import edu.project3.reports.LogReportUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogReportUtilsTest {
    private static final List<LogRecord> sampleLogRecords = createSampleLogRecords();

    @Test
    void testThatComputeAverageResponseSizeReturnedCorrectValue() {
        String averageResponseSize = LogReportUtils.computeAverageResponseSize(sampleLogRecords);
        assertThat(averageResponseSize).isEqualTo("3");
    }

    @Test
    void testThatGenerateResourceTableReturnedCorrectTable() {
        String resourceTable = LogReportUtils.generateResourceTable(sampleLogRecords, "format");
        assertThat(resourceTable).isEqualTo("|/downloads/product_1| 2");
    }

    @Test
    void testThatGenerateStatusCodesTableReturnedCorrectTable() {
        String statusCodesTable = LogReportUtils.generateStatusCodesTable(sampleLogRecords, "format");
        assertThat(statusCodesTable).isEqualTo("|304|NOT_MODIFIED|1" + "\n" + "|200|OK|1");
    }

    @Test
    void testThatGenerateTopIpAddressesTableReturnedCorrectTable() {
        String topIpAddressesTable = LogReportUtils.generateTopIpAddressesTable(sampleLogRecords, "format");
        assertThat(topIpAddressesTable).isEqualTo("|93.180.71.3|1" + "\n" + "|93.180.71.4|1");
    }

    @Test
    void testThatGenerateRequestTableReturnedCorrectTable() {
        String requestTable = LogReportUtils.generateRequestTable(sampleLogRecords, "format");
        assertThat(requestTable).isEqualTo("|GET|2");
    }

    private static List<LogRecord> createSampleLogRecords() {
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
