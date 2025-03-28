package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                        String message = extractMessage(string);
                        switch (message) {
                            case "Hello" -> output.write("Hello!".getBytes());
                            case "Exit" -> server.close();
                            default -> output.write(message.getBytes());
                        }
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private static String extractMessage(String line) {
        Pattern pattern = Pattern.compile("\\s/\\?msg=(.+)\\s");
        Matcher matcher = pattern.matcher(line);
        return matcher.find() ? matcher.group(1) : "";
    }
}
