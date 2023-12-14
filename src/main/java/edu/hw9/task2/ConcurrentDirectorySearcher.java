package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentDirectorySearcher extends RecursiveTask<List<Path>> {

    private final Path currentPath;
    private final int minFilesInDirectory;

    public ConcurrentDirectorySearcher(Path currentPath, int minFilesInDirectory) {
        this.currentPath = currentPath;
        this.minFilesInDirectory = minFilesInDirectory;
    }

    @Override public List<Path> compute() {
        List<ConcurrentDirectorySearcher> tasks = new ArrayList<>();
        List<Path> matchingDirectories = new ArrayList<>();
        AtomicInteger fileCount = new AtomicInteger();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentPath)) {
            for (Path entry : directoryStream) {
                if (Files.isDirectory(entry)) {
                    ConcurrentDirectorySearcher task = new ConcurrentDirectorySearcher(entry, minFilesInDirectory);
                    tasks.add(task);
                    task.fork();
                } else {
                    fileCount.incrementAndGet();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fileCount.get() >= minFilesInDirectory) {
            matchingDirectories.add(currentPath);
        }

        for (ConcurrentDirectorySearcher task : tasks) {
            matchingDirectories.addAll(task.join());
        }
        return matchingDirectories;
    }
}
