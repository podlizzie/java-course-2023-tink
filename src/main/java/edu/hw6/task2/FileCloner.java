package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class FileCloner {
    private FileCloner() {

    }

    /**
     * Clones the specified file by creating a copy with a unique name in the same directory.
     *
     * @param path The path to the file to be cloned.
     * @throws IOException if an I/O error occurs when creating the file copy.
     */
    public static void cloneFile(@NotNull Path path) throws IOException {
        String fileName = path.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf("."));

        int count = 1;
        Path newFilePath = path.resolveSibling(baseName + extension);
        while (Files.exists(newFilePath)) {
            newFilePath = path.resolveSibling(baseName
                + " — копия" + (count > 1 ? " (" + count + ")" : "") + extension);
            count++;
        }

        Files.copy(path, newFilePath);
    }
}
