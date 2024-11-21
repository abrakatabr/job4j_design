package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s()",
                    tableName
            );
            tryStatement(sql);
    }

    public void dropTable(String tableName) {
            String sql = String.format(
                    "DROP TABLE %s",
                    tableName
            );
            tryStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s",
                    tableName,
                    columnName,
                    type
            );
            tryStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s",
                    tableName,
                    columnName
            );
            tryStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s to %s",
                    tableName,
                    columnName,
                    newColumnName
            );
            tryStatement(sql);
    }

    private void tryStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TableEditor tableEditor = new TableEditor(config);
            String tableName = "animals";
            tableEditor.createTable(tableName);
            tableEditor.addColumn(tableName, "age", "text");
            tableEditor.addColumn(tableName, "name", "text");
            tableEditor.addColumn(tableName, "count", "int");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.renameColumn(tableName, "age", "animal_age");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.dropColumn(tableName, "animal_age");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.dropTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
