package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    private ArgsName argsName;

    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        reader.validation(args);
        var data = reader.readData(
                reader.argsName.get("path"),
                reader.argsName.get("delimiter")
        );
        var result = reader.filter(data);
        reader.print(result);
    }

    private List<List<String>> readData(String path, String delimiter) {
        List<List<String>> data = new ArrayList<>();
        try (Scanner in = new Scanner(new File(path))) {
            while (in.hasNextLine()) {
                data.add(parseStringToTokens(in.nextLine(), delimiter));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    private List<String> parseStringToTokens(String str, String delimiter) {
        Scanner lineScanner = new Scanner(str).useDelimiter(delimiter);
        List<String> tokens = new ArrayList<>();
        while (lineScanner.hasNext()) {
            tokens.add(lineScanner.next());
        }
        return tokens;
    }

    private List<String> filter(List<List<String>> data) {
        List<Integer> columns = extractColumnNumbers(data.get(0));
        List<String> res = new ArrayList<>();
        data.forEach(list -> {
            StringJoiner sj = new StringJoiner(";");
            columns.forEach(col -> sj.add(list.get(col)));
            res.add(sj.toString());
        });
        return res;
    }

    private List<Integer> extractColumnNumbers(List<String> head) {
        List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < head.size(); i++) {
            if (argsName.get("filter").contains(head.get(i))) {
                columns.add(i);
            }
        }
        return columns;
    }

    private void print(List<String> strings) {
        try (OutputStream outputStream =
                     "stdout".equals(argsName.get("out"))
                             ? System.out : new BufferedOutputStream(new FileOutputStream(argsName.get("out")));
             PrintWriter pw = new PrintWriter(
                     new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            strings.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validation(String[] args) {
        argsName = ArgsName.of(args);
        Set<String> argsKeys = argsName.keySet();
        if (!(args.length == 4
                && argsKeys.contains("path")
                && argsKeys.contains("delimiter")
                && argsKeys.contains("out")
                && argsKeys.contains("filter"))) {
            throw new IllegalArgumentException("Invalid arguments. "
                    + "Use java -jar CSVReader.jar -path=<INPUT_FILE> -delimiter=<DELIMITER> "
                    + "-out=<stdout / OUTPUT_FILE> -filter=COLUMN1[,COLUMN2...]");
        }
        Path path = Path.of(argsName.get("path"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("File does not exist%s", path));
        }
    }
}
