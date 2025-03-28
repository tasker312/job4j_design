package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines()
                    .map(this::getUserByString)
                    .forEach(users::add);
        }
        return users;
    }

    private User getUserByString(String line) {
        String[] data = line.trim().split(";");
        if (data.length != 2 || data[0].isEmpty() || data[1].isEmpty()) {
            throw new IllegalArgumentException("Invalid user line: " + line);
        }
        return new User(data[0], data[1]);
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("spammer.jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("spammer.jdbc.url"),
                config.getProperty("spammer.jdbc.username"),
                config.getProperty("spammer.jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}
