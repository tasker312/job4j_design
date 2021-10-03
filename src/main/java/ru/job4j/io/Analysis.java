package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean isAvailable = true;
            for (String notice = br.readLine(); notice != null; notice = br.readLine()) {
                String[] data = notice.split(" ");
                int status = Integer.parseInt(data[0]);
                String date = data[1];
                if ((status == 400 || status == 500) && isAvailable) {
                    out.print(date + ";");
                    isAvailable = false;
                } else if ((status == 200 || status == 300) && !isAvailable) {
                    out.println(date + ";");
                    isAvailable = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analysis().unavailable("data/server_log.csv", "data/unavailable.csv");
    }
}
