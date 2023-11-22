package edu.hw6.task2test;

import edu.hw6.task2.FileCloner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileClonerTest {

    @Test
    public void testThatTXTFileClonedWithNewName() throws IOException {
        Path originalFile = Paths.get("src/test/java/edu/hw6/task2test/Tinkoff Bank Biggest Secret.txt");
        FileCloner.cloneFile(originalFile);

        Path clonedFile = Paths.get("src/test/java/edu/hw6/task2test/Tinkoff Bank Biggest Secret — копия.txt");

        assertThat(Files.exists(clonedFile)).isTrue();
        assertThat(Files.readAllLines(originalFile)).isEqualTo(Files.readAllLines(clonedFile));
    }

    @Test
    public void testThatDOCXFileClonedWithNewNameMultipleTimes() throws IOException {
        Path originalFile = Paths.get("src/test/java/edu/hw6/task2test/Very Important Things.docx");
        FileCloner.cloneFile(originalFile);
        FileCloner.cloneFile(originalFile);

        Path clonedFile1 = Paths.get("src/test/java/edu/hw6/task2test/Very Important Things — копия.docx");
        Path clonedFile2 = Paths.get("src/test/java/edu/hw6/task2test/Very Important Things — копия (2).docx");

        assertThat(Files.exists(clonedFile1)).isTrue();
        assertThat(Files.exists(clonedFile2)).isTrue();
    }
}
