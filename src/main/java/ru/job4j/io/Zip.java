package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target)
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

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validateArgs(argsName);
        List<Path> paths = null;
        try {
            Search searcher = new Search();
            paths = searcher.search(Paths.get(argsName.get("d")), x -> !x.toFile().getName().endsWith(argsName.get("e")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.packFiles(paths, new File(argsName.get("o")));
    }

    private void validateArgs(ArgsName args) {
        try {
            boolean isValid = true;
            isValid &= Files.isDirectory(Paths.get(args.get("d")));
            isValid &= args.get("e").startsWith(".");
            isValid &= args.get("o").endsWith(".zip");
            if (!isValid) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid arguments."
                    + e.getMessage() + " "
                    + " Use java -jar Zip.jar -d=<DIRECTORY> -e=.<FILE_EXTENSION> -o=<TARGET_NAME>.zip");
        }
    }
}
