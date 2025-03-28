package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String entry : args) {
            if (!entry.startsWith("-")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not start with a '-' character", entry));
            }
            String[] data = entry.split("=", 2);
            if (data.length != 2) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain an equal sign", entry)
                );
            }
            if (data[0].length() == 1) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a key", entry)
                );
            }
            if (data[1].isEmpty()) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a value", entry)
                );
            }
            values.put(data[0].substring(1), data[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
