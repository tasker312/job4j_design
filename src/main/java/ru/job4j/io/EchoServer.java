package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (in.ready()) {
                        String message = extractMessage(in.readLine());
                        switch (message) {
                            case "Hello" -> out.write("Hello!".getBytes());
                            case "Exit" -> server.close();
                            default -> out.write(message.getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    private static String extractMessage(String line) {
        return line.contains("/?msg=")
                ? line.substring(line.indexOf("/?msg=") + 6, line.lastIndexOf(' ')) : "";
    }
}
