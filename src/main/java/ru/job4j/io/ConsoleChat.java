package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isStopped = false;
            String line;
            do {
                line = br.readLine();
                if (STOP.equals(line)) {
                    isStopped = true;
                } else if (CONTINUE.equals(line)) {
                    isStopped = false;
                }

                log.add(line);
                if (!isStopped) {
                    String answer = phrases.get(new Random().nextInt(phrases.size()));
                    System.out.println(answer);
                    log.add(answer);
                }
            } while (!OUT.equals(line));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = null;
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            phrases = br.lines().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/console_chat.txt", "data/bot_phrases.txt");
        consoleChat.run();
    }
}
