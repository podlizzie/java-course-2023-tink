package edu.hw6.task1test;

import edu.hw6.task1.FileManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileManagerTest {

    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/task1test/testFile.txt";
    private final static Logger LOGGER = LogManager.getLogger();

    @Test
    public void testSaveToFileShouldWriteMapContentToFile() throws IOException {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");

        FileManager.saveToFile(TEST_FILE_PATH, testMap);

        File file = new File(TEST_FILE_PATH);
        String fileContent = Files.readString(file.toPath());
        assertThat(fileContent).contains("key1:value1", "key2:value2");
    }

    @Test
    public void testLoadFromFileShouldFillMapWithFileContent() {
        File file = new File(TEST_FILE_PATH);
        try {
            Files.writeString(file.toPath(), "key3:value3\nkey4:value4");
        } catch (IOException e) {
            LOGGER.info(e);
        }

        Map<String, String> testMap = new HashMap<>();
        FileManager.loadFromFile(TEST_FILE_PATH, testMap);

        assertThat(testMap).containsOnly(
            Map.entry("key3", "value3"),
            Map.entry("key4", "value4")
        );
    }

}
