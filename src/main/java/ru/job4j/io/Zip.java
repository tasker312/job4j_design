package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static ArgsName argsNames;

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target.toFile())
                ))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath().substring(2)));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source.toFile())
                )) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        Path root = Path.of(argsNames.get("d"));
        List<Path> files = searchFiles(root);
        packFiles(files, Path.of(argsNames.get("o")));
    }

    private static List<Path> searchFiles(Path root) throws IOException {
        return Search.search(
                        root,
                        path -> !path.toFile().getName().endsWith("." + argsNames.get("e"))
                ).stream()
                    .map(String::valueOf)
                    .map(str -> "." + str.substring(argsNames.get("d").length()))
                    .map(Path::of)
                    .collect(Collectors.toList());
    }

    private static void validation(String[] args) {
        argsNames = ArgsName.of(args);
        Set<String> argsKeys = argsNames.keySet();
        if (!(args.length == 3
                && argsKeys.contains("d")
                && argsKeys.contains("e")
                && argsKeys.contains("o"))) {
            throw new IllegalArgumentException("Invalid arguments."
                    + "Use java -jar Zip.jar -d=<DIRECTORY> -e=<FILE_EXTENSION> -o=<TARGET_NAME>");
        }
        Path root = Path.of(argsNames.get("d"));
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Root does not exist%s", root));
        }
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Root is not directory %s", root));
        }
    }
}
