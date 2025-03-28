package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("M:\\"), visitor);
        printDuplicates(visitor.getDuplicates());
    }

    private static void printDuplicates(Map<FileProperty, List<Path>> duplicates) {
        System.out.printf("%n%nRESULTS:%n%n");
        duplicates.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .filter(e -> e.getKey().getSize() > 20 * 1024 * 1024)
                .forEach(e -> {
                    System.out.printf("Duplicates for \"%s\" (%d KB)%n",
                            e.getKey().getName(),
                            e.getKey().getSize() / 1024);
                    e.getValue().forEach(System.out::println);
                    System.out.println();
                });
    }
}
