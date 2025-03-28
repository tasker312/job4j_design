package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pw = new PrintWriter(new FileOutputStream(target))) {
            boolean isAvailable = true;
            while (br.ready()) {
                String[] data = br.readLine().split(" ");
                if (isAvailable && ("400".equals(data[0]) || "500".equals(data[0]))) {
                    pw.print(data[1] + ";");
                    isAvailable = false;
                } else if (!isAvailable && ("200".equals(data[0]) || "300".equals(data[0]))) {
                    pw.println(data[1] + ";");
                    isAvailable = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
