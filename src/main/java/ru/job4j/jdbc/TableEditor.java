package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password")
        );
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        executeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table %s",
                tableName
        );
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s add if not exists %s %s",
                tableName, columnName, type
        );
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s",
                tableName, columnName
        );
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName, columnName, newColumnName
        );
        executeStatement(sql);
    }

    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Properties properties = readProperties("./src/main/resources/app.properties");
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("test");
            tableEditor.addColumn("test", "id", "serial primary key");
            tableEditor.addColumn("test", "name", "text");
            tableEditor.renameColumn("test", "name", "newName");
            tableEditor.dropColumn("test", "id");
            tableEditor.dropTable("test");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Properties readProperties(String path) {
        Config config = new Config(path);
        config.load();
        Properties properties = new Properties();
        properties.put("driver", config.value("driver"));
        properties.put("url", config.value("url"));
        properties.put("login", config.value("login"));
        properties.put("password", config.value("password"));
        return properties;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
