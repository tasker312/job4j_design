package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] data = text.toString().split(System.lineSeparator());
        Arrays.stream(data)
                .map(Integer::valueOf)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);
    }
}
