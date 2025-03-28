package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        var data = readData(argsName.get("path"), argsName.get("delimiter"));
        var filtered = filter(data, argsName.get("filter"));
        print(filtered, argsName.get("out"), argsName.get("delimiter"));
    }

    private static List<List<String>> readData(String path, String delimiter) {
        List<List<String>> data = new ArrayList<>();
        try (Scanner lineScanner = new Scanner(new File(path))) {
            while (lineScanner.hasNextLine()) {
                Scanner itemScanner = new Scanner(lineScanner.nextLine()).useDelimiter(delimiter);
                data.add(itemScanner.tokens().toList());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static List<List<String>> filter(List<List<String>> data, String columns) {
        List<Integer> columnIndexes = extractColumnIndexes(data.get(0), columns);
        return data.stream()
                .map(line -> columnIndexes.stream()
                        .map(line::get)
                        .toList()
                )
                .toList();
    }

    private static List<Integer> extractColumnIndexes(List<String> head, String columns) {
        return Arrays.stream(columns.split(","))
                .map(head::indexOf)
                .toList();
    }

    private static void print(List<List<String>> data, String output, String delimiter) {
        try (OutputStream outputStream =
                     "stdout".equals(output) ? System.out : new BufferedOutputStream(new FileOutputStream(output));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))
        ) {
            data.stream()
                    .map(line -> String.join(delimiter, line))
                    .forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName args) {
        try {
            boolean isValid = true;
            isValid &= Files.isRegularFile(Paths.get(args.get("path")));
            isValid &= args.get("path").endsWith(".csv");
            isValid &= !args.get("delimiter").isEmpty();
            isValid &= !args.get("out").isEmpty();
            isValid &= !args.get("filter").isEmpty();
            if (!isValid) {
                throw new IllegalArgumentException("test");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid arguments. "
                    + e.getMessage() + ". "
                    + "Use java -jar CSVReader.jar -path=<INPUT_FILE> -delimiter=<DELIMITER> "
                    + "-out=<stdout / OUTPUT_FILE> -filter=COLUMN1[,COLUMN2...]");
        }
    }
}
