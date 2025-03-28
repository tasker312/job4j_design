package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.login"),
                properties.getProperty("jdbc.password")
        );
    }

    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE %s();", tableName);
        executeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        executeStatement(sql);
    }

    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            props.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try (TableEditor editor = new TableEditor(props)) {
            editor.createTable("new_table");
            System.out.println(editor.getTableScheme("new_table"));

            editor.addColumn("new_table", "id", "int");
            editor.addColumn("new_table", "name", "varchar(255)");
            editor.addColumn("new_table", "second_name", "varchar(255)");
            System.out.println(editor.getTableScheme("new_table"));

            editor.renameColumn("new_table", "second_name", "last_name");
            editor.dropColumn("new_table", "name");
            System.out.println(editor.getTableScheme("new_table"));

            editor.dropTable("new_table");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
