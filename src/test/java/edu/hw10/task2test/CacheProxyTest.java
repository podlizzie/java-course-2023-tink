package edu.hw10.task2test;

import edu.hw10.task2.CacheProxy;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheProxyTest {
    @TempDir
    Path tempDir;
    private FibCalculator cachingCalculator;

    @BeforeEach
    public void beforeEach() {
        FibCalculator calculator = new FibCalculatorRealise();
        cachingCalculator = CacheProxy.create(calculator, calculator.getClass(), tempDir);
    }

    @AfterEach
    public void afterEach() throws IOException {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(tempDir)) {
            for (Path path : paths) {
                Files.deleteIfExists(path);
            }
        }
    }

    @Test
    public void testThatCachingWorksCorrectly() {
        //given
        //when
        int expectedNum = 233;

        long firstStartTime = System.nanoTime();
        assertThat(cachingCalculator.fib(12)).isEqualTo(expectedNum);
        long firstWorkingTime = System.nanoTime() - firstStartTime;

        long secondStartTime = System.nanoTime();
        assertThat(cachingCalculator.fib(12)).isEqualTo(expectedNum);
        long secondWorkingTime = System.nanoTime() - secondStartTime;

        //then
        assertTrue(secondWorkingTime < firstWorkingTime);
    }

    @Test
    public void testThatPersistWorksCorrectly() throws IOException {
        //given
        cachingCalculator.fib(0);
        cachingCalculator.fib(1);
        cachingCalculator.fib(2);
        cachingCalculator.fib(2);

        //when
        int filesCount = 0;
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(tempDir)) {
            for (Path ignored : paths) {
                filesCount++;
            }
        }

        //then
        assertThat(filesCount).isEqualTo(3);
    }
}
