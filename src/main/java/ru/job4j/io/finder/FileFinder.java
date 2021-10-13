package ru.job4j.io.finder;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFinder {
    private ArgsName arg;

    public static void main(String[] args) {
        FileFinder finder = new FileFinder();
        finder.validation(args);
        var foundedFiles = finder.find();
        var filteredFiles = finder.filter(foundedFiles);
        finder.print(filteredFiles);
    }

    private List<Path> find() {
        FileVisitor visitor = new FileVisitor();
        try {
            Files.walkFileTree(Path.of(arg.get("d")), visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.getPaths();
    }

    private List<Path> filter(List<Path> paths) {
        return new FileFilter(paths).filter(getPredicate());
    }

    private Predicate<Path> getPredicate() {
        String name = arg.get("n");
        return switch (arg.get("t")) {
            case "name" -> path -> name.equals(path.toFile().getName());
            case "mask" -> {
                String maskName = name.replaceAll("\\.", "\\\\.")
                        .replaceAll("\\?", ".{1}")
                        .replaceAll("\\*", ".*");
                Pattern pattern = Pattern.compile(maskName);
                yield path -> pattern.matcher(path.toFile().getName()).matches();
            }
            case "regex" -> {
                Pattern pattern = Pattern.compile(name);
                yield path -> pattern.matcher(path.toFile().getName()).matches();
            }
            default -> throw new IllegalArgumentException();
        };
    }

    private void print(List<Path> paths) {
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(arg.get("o"))))) {
            paths.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validation(String[] args) {
        arg = ArgsName.of(args);
        Set<String> argsKeys = arg.keySet();
        if (!(args.length == 4
                && argsKeys.contains("d")
                && argsKeys.contains("n")
                && argsKeys.contains("t")
                && argsKeys.contains("o"))) {
            throw new IllegalArgumentException("Invalid arguments. "
                    + "Use java -jar FileFinder.jar -d=<DIRECTORY_PATH> -n=<NAME> "
                    + "-t=<FILTER_TYPE> -o=<OUTPUT_PATH>\n"
                    + "FILTER_TYPE can be:\n"
                    + "\tname - for searching exactly by name;\n"
                    + "\tmask - for searching by mask;\n"
                    + "\tregex - for searching by RegEx.");
        }
        Path root = Path.of(arg.get("d"));
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Root does not exist%s", root));
        }
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Root is not directory %s", root));
        }
    }
}
