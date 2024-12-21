package org.example;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:games_database.db";
    private Connection connection;

    public Database(){
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
        }
    }

    public void createTables() {
        try (Statement stmt = connection.createStatement()) {
            String table = "CREATE TABLE IF NOT EXISTS games ("
                    + "rank INTEGER PRIMARY KEY,"
                    + "name TEXT NOT NULL,"
                    + "platform TEXT NOT NULL,"
                    + "year INTEGER,"
                    + "genre TEXT,"
                    + "publisher TEXT,"
                    + "na_sales REAL,"
                    + "eu_sales REAL,"
                    + "jp_sales REAL,"
                    + "other_sales REAL,"
                    + "global_sales REAL)";
            stmt.execute(table);
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблиц: " + e.getMessage());
        }
    }

    public void insertGame(int rank, String name, String platform, int year, String genre, String publisher,
                           double naSales, double euSales, double jpSales, double otherSales, double globalSales) {
        String sql = "INSERT INTO games(rank, name, platform, year, genre, publisher, na_sales, eu_sales, jp_sales, other_sales, global_sales) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, rank);
            pstmt.setString(2, name);
            pstmt.setString(3, platform);
            pstmt.setInt(4, year);
            pstmt.setString(5, genre);
            pstmt.setString(6, publisher);
            pstmt.setDouble(7, naSales);
            pstmt.setDouble(8, euSales);
            pstmt.setDouble(9, jpSales);
            pstmt.setDouble(10, otherSales);
            pstmt.setDouble(11, globalSales);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при вставке данных: " + e.getMessage());
        }
    }

    public void clearTables() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM games");

            System.out.println("Таблица очищена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
}
