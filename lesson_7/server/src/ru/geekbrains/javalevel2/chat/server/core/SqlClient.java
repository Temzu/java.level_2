package ru.geekbrains.javalevel2.chat.server.core;


import java.sql.*;

public class SqlClient {
    private static Connection connection;
    private static Statement statement;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:server/chat-server.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException exception) {
            throw new RuntimeException();
        }
    }

    synchronized static String getNickname(String login, String password) {
        String query = String.format("select nickname from clients where login = '%s' and password = '%s'",
                login, password);

        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next())
                return set.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
