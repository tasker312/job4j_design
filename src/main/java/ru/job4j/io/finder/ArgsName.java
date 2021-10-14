package ru.job4j.io.finder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    private ArgsName() {
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(arg -> arg.split("="))
                .forEach(data -> {
                    if (data.length != 2
                            || data[0].isEmpty()
                            || !data[0].startsWith("-")
                            || data[1].isEmpty()) {
                        throw new IllegalArgumentException("Invalid program arguments. Use [-<KEY>=<VALUE>]");
                    }
                    values.put(data[0].substring(1), data[1]);
                });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public Set<String> keySet() {
        return values.keySet();
    }
}
