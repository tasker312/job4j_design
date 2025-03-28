package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments. "
                    + "Use java -jar Search.jar <ROOT_FOLDER> <FILE_EXTENSION>");
        }
        Path path = Paths.get(args[0]);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Root does not exist " + path.toAbsolutePath());
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Root is not a directory " + path.toAbsolutePath());
        }
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        Search searcher = new Search();
        searcher.validate(args);
        searcher.search(Paths.get(args[0]), path -> path.toFile().getName().endsWith("." + args[1])).forEach(System.out::println);
    }
}
