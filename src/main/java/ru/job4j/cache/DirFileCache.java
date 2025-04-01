package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        validate(cachingDir, Files::isDirectory);
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        validate(cachingDir + "/" + key, Files::isRegularFile);
        try (BufferedReader br = new BufferedReader(new FileReader(cachingDir + "/" + key))) {
            String file = br.lines().collect(Collectors.joining(System.lineSeparator()));
            super.put(key, file);
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Cannot load file: " + key);
        }
    }

    private static void validate(String str, Predicate<Path> condition) {
        Path path = Path.of(str);
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Does not exist: " + path.toAbsolutePath());
        }
        if (!condition.test(path)) {
            throw new IllegalArgumentException("Invalid path: " + path.toAbsolutePath());
        }
    }
}
