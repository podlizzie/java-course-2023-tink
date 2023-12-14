package edu.hw9;

import edu.hw9.task2.ConcurrentDirectorySearcher;
import edu.hw9.task2.ConcurrentFileSearcher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Task2Test {
    private Path testDirectory;

    public void setUp() throws IOException {
        testDirectory = Files.createTempDirectory("testDir");
        Files.createFile(testDirectory.resolve("file1.txt"));
        Files.createDirectory(testDirectory.resolve("dir1"));
        Files.createFile(testDirectory.resolve("dir1/file2.txt"));
        Files.createFile(testDirectory.resolve("dir1/file3.txt"));
    }

    @Test
    public void testThatConcurrentFileSearcherReturnedMatchingFiles() throws IOException {
        // given
        setUp();
        Predicate<Path> predicate = path -> path.toString().endsWith(".txt");
        ConcurrentFileSearcher fileSearcher = new ConcurrentFileSearcher(testDirectory, List.of(predicate));

        // when
        List<Path> matchingFiles = fileSearcher.compute();

        // then
        assertThat(matchingFiles)
            .hasSize(3)
            .allMatch(predicate);
    }

    @Test
    public void testThatConcurrentDirectorySearcherReturnedDirectoriesWithMinFiles() throws IOException {
        setUp();
        // given
        int minFilesInDirectory = 2;
        ConcurrentDirectorySearcher
            directorySearcher = new ConcurrentDirectorySearcher(testDirectory, minFilesInDirectory);

        // when
        List<Path> matchingDirectories = directorySearcher.compute();

        // then
        assertThat(matchingDirectories)
            .hasSize(1)
            .containsExactly(testDirectory.resolve("dir1"));
    }
}
