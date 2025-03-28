package ru.job4j.io;

import java.io.*;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            list = br.lines()
                    .filter(line -> {
                        String[] data = line.split(" ");
                        return "404".equals(data[data.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            data.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
