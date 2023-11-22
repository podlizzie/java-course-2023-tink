package edu.hw6.task3test;

import edu.hw6.task3.Filter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FilterTest {

    private static final Path TEST_DIR = Paths.get("src/test/java/edu/hw6/task3test/testDir");

    private List<String> applyFilter(DirectoryStream.Filter<Path> filter) throws IOException {
        List<String> actualFiles = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach(filePath ->
                actualFiles.add(filePath.getFileName().toString()));
        }
        return actualFiles;
    }

    @Test
    @DisplayName("Test that readable filter returns true for a readable file")
    void testThatReadableReturnedTrueForReadableFile() throws IOException {
        List<String> expectedFiles = List.of("kitty.png", "meow_murmur.png", "test.txt");
        DirectoryStream.Filter<Path> filter = Filter.READABLE;

        List<String> actualFiles = applyFilter(filter);

        assertThat(actualFiles).containsAll(expectedFiles);
    }

    @Test
    @DisplayName("Test that largerThan filter returns true for a file larger than specified size")
    void testThatLargerThanReturnedTrueForLargerFile() throws IOException {
        List<String> expectedFiles = List.of("kitty.png", "meow_murmur.png");
        DirectoryStream.Filter<Path> filter = Filter.largerThan(800 * 530);

        List<String> actualFiles = applyFilter(filter);

        assertThat(actualFiles).containsAll(expectedFiles);
    }

    @Test
    @DisplayName("Test that magicNumber filter returns true for a file with specified magic number")
    void testThatMagicNumberReturnedTrueForFileWithMagicNumber() throws IOException {
        List<String> expectedFiles = List.of("kitty.png", "meow_murmur.png");
        DirectoryStream.Filter<Path> filter = Filter.magicNumber((char) -119, (char) 80);

        List<String> actualFiles = applyFilter(filter);

        assertThat(actualFiles).containsAll(expectedFiles);
    }

    @Test
    @DisplayName("Test that globMatches filter returns true for a file matching the glob pattern")
    void testThatGlobMatchesReturnedTrueForFileMatchingPattern() throws IOException {
        String expectedFile = "test.txt";
        DirectoryStream.Filter<Path> filter = Filter.globMatches("*.txt");

        List<String> actualFiles = applyFilter(filter);

        assertThat(actualFiles).containsExactly(expectedFile);
    }

    @Test
    @DisplayName("Test that regexContains filter returns true for a file with name matching the regex pattern")
    void testThatRegexContainsReturnedTrueForFileMatchingPattern() throws IOException {
        String expectedFile = "kitty.png";
        DirectoryStream.Filter<Path> filter = Filter.regexContains(".*kit.*");

        List<String> actualFiles = applyFilter(filter);

        assertThat(actualFiles).containsExactly(expectedFile);
    }

    @Test
    @DisplayName("Test that integration of all filters returns correct result")
    void testThatIntegrationOfAllFiltersReturnedCorrectResult() throws IOException {
        String expectedFile = "meow_murmur.png";

        DirectoryStream.Filter<Path> filter = Filter.REGULAR_FILE
            .and(Filter.READABLE)
            .and(Filter.largerThan(800 * 530))
            .and(Filter.magicNumber((char) -119, (char) 80))
            .and(Filter.globMatches("*.png"))
            .and(Filter.regexContains(".*meow.*"));

        List<String> actualFiles = applyFilter(filter);

        assertThat(actualFiles).containsExactly(expectedFile);
    }
}
