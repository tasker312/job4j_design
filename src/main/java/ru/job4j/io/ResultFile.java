package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ResultFile {
    public static void main(String[] args) {
        int[][] matrix = multiple(9);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    out.write(String.format("%3d", anInt).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }
}
