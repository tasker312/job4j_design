package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String res = values.get(key);
        if (res == null) {
            throw new IllegalArgumentException();
        }
        return res;
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(arg -> arg.split("="))
                .forEach(data -> {
                    if (data.length != 2
                            || data[0].isEmpty()
                            || !data[0].startsWith("-")
                            || data[1].isEmpty()) {
                        throw new IllegalArgumentException("Invalid arguments");
                    }
                    values.put(data[0].substring(1), data[1]);
                });
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
