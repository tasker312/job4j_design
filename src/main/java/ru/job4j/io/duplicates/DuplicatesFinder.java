package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("D:\\1"), visitor);
        var duplicates = visitor.getFiles();
        duplicates.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> {
                    System.out.printf("Duplicates for \"%s\" (%d KB)%n",
                            entry.getKey().getName(),
                            entry.getKey().getSize() / 1024);
                    entry.getValue().forEach(System.out::println);
                    System.out.println();
                });
    }
}
