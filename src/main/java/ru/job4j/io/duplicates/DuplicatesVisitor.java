package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> duplicates = new HashMap<>();
    private final Predicate<Path> filter;

    public DuplicatesVisitor() {
        filter = x -> true;
    }

    public DuplicatesVisitor(Predicate<Path> filter) {
        this.filter = filter;
    }

    public Map<FileProperty, List<Path>> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(Files.size(file), file.getFileName().toString());
        duplicates.computeIfAbsent(currentFile, paths -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {
        if (!filter.test(file)) {
            System.out.printf("Skipping: %s%n", file);
            return SKIP_SUBTREE;
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        if (exc != null) {
            System.out.printf("Skipping: %s -- %s%n", file, exc.getClass().getSimpleName());
            return SKIP_SUBTREE;
        }
        return CONTINUE;
    }
}
