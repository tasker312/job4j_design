package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] data = text.toString().split(System.lineSeparator());
        Arrays.stream(data)
                .map(Integer::valueOf)
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);
    }
}
