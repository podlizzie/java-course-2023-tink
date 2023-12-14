package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class ConcurrentFileSearcher extends RecursiveTask<List<Path>> {

    private final Path currentPath;
    private final List<Predicate<Path>> predicates;

    public ConcurrentFileSearcher(Path currentPath, List<Predicate<Path>> predicates) {
        this.currentPath = currentPath;
        this.predicates = predicates;
    }

    @Override public List<Path> compute() {
        List<ConcurrentFileSearcher> tasks = new ArrayList<>();
        List<Path> matchingFiles = new ArrayList<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentPath)) {
            for (Path entry : directoryStream) {
                if (Files.isDirectory(entry)) {
                    ConcurrentFileSearcher task = new ConcurrentFileSearcher(entry, predicates);
                    tasks.add(task);
                    task.fork();
                } else if (matchesPredicates(entry)) {
                    matchingFiles.add(entry);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (ConcurrentFileSearcher task : tasks) {
            matchingFiles.addAll(task.join());
        }
        return matchingFiles;
    }

    private boolean matchesPredicates(Path file) {
        return predicates.stream().allMatch(predicate -> predicate.test(file));
    }
}
