package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        search(Path.of(args[0]), p -> p.toFile().getName().endsWith("." + args[1])).forEach(System.out::println);
    }

    private static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments. "
                    + "Use java -jar Search.jar <ROOT_FOLDER> <FILE_EXTENSION>");
        }
        Path path = Path.of(args[0]);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(
                    String.format("Root does not exist %s", path));
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Root is not directory %s", path));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
