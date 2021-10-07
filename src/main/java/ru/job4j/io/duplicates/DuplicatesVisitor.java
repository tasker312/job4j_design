package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, FileProperty> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(), file.toFile().getName(), file);
        if (files.putIfAbsent(currentFile, currentFile) != null) {
            var oldFile = files.get(currentFile);
            System.out.printf("File %s is duplicate for %s (%dKB).%n",
                    file.toFile().getAbsolutePath(),
                    oldFile.getPath(),
                    oldFile.getSize() / 1024
            );
        }
        return super.visitFile(file, attrs);
    }
}
