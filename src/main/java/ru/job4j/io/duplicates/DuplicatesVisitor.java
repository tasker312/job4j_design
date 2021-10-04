package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(), file.toFile().getName(), file);
        if (!files.add(currentFile)) {
            var oldFile =
                    files.stream()
                            .filter(property -> property.equals(currentFile))
                            .findFirst()
                            .get();
            System.out.printf("File %s is duplicate for %s (%dKB).%n",
                    file.toFile().getAbsolutePath(),
                    oldFile.getPath(),
                    oldFile.getSize() / 1024
            );
        }
        return super.visitFile(file, attrs);
    }
}
