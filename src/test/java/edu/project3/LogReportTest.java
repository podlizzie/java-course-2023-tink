package edu.project3;

import edu.project3.reports.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogReportTest {
    @Test
    void thatWriteReportToADOCFileCalledWithCorrectArguments() throws IOException {
        String reportContent = "example report content";
        String outputFormat = "adoc";
        String fileName = "example.adoc";
        String folderPath = "src/main/java/edu/project3/tablesExamples";

        Path folder = Paths.get(folderPath);
        Path file = folder.resolve(fileName);
        if (Files.exists(file)) {
            Files.delete(file);
        }

        LogReport.writeReportToFile(reportContent, outputFormat);
        String writtenContent = Files.readString(file);

        assertThat(reportContent).isEqualTo(writtenContent);
    }

    @Test
    void thatWriteReportToMarkdownFileCalledWithCorrectArguments() throws IOException {
        String reportContent = "example report content";
        String outputFormat = "markdown";
        String fileName = "example.md";
        String folderPath = "src/main/java/edu/project3/tablesExamples";

        Path folder = Paths.get(folderPath);
        Path file = folder.resolve(fileName);
        if (Files.exists(file)) {
            Files.delete(file);
        }

        LogReport.writeReportToFile(reportContent, outputFormat);
        String writtenContent = Files.readString(file);

        assertThat(reportContent).isEqualTo(writtenContent);
    }

}
