package edu.hw6.task4test;

import edu.hw6.task4.ChainConstruction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.CRC32;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChainConstructionTest {
    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/task4test/testFile.txt";
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testThatWriteTextToFileReturnedNewFileWithText() {
        String filePath = TEST_FILE_PATH;
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        ChainConstruction.writeTextToFile(filePath, text);

        String content = getFileContent(filePath);
        assertThat(content).isEqualTo(text);
    }

    @Test
    public void testThatChecksumIsCorrect() {
        String filePath = TEST_FILE_PATH;
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        ChainConstruction.writeTextToFile(filePath, text);

        long expectedChecksum = getChecksum(text);
        long actualChecksum = getChecksum(Objects.requireNonNull(getFileContent(filePath)));

        assertThat(actualChecksum).isEqualTo(expectedChecksum);
    }

    private String getFileContent(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (IOException e) {
            LOGGER.info(e);
            return null;
        }
    }

    private long getChecksum(String text) {
        CRC32 crc32 = new CRC32();
        crc32.update(text.getBytes());
        return crc32.getValue();
    }
}
