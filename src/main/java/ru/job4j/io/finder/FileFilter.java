package ru.job4j.io.finder;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileFilter {
    private final List<Path> files;

    public FileFilter(List<Path> files) {
        this.files = files;
    }

    public List<Path> filter(Predicate<Path> predicate) {
        return files.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
