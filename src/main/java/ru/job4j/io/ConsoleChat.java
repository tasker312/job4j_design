package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        boolean isStopped = false;
        String line;
        do {
            line = in.nextLine();
            log.add(">" + line);
            if (STOP.equals(line)) {
                isStopped = true;
            } else if (CONTINUE.equals(line)) {
                isStopped = false;
            }
            if (!isStopped) {
                int rnd = new Random().nextInt(answers.size());
                System.out.println(answers.get(rnd));
                log.add("<" + answers.get(rnd));
            }
        } while (!OUT.equals(line));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/bot_log.txt", "./data/bot_phrases.txt");
        cc.run();
    }
}
