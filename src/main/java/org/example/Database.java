package org.example;

import java.sql.*;
import java.util.List;

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

    public Connection getConnection() {
        return connection;
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

    public void insertGame(List<Game> games) {
        String sql = "INSERT INTO games(rank, name, platform, year, genre, publisher, na_sales, eu_sales, jp_sales, other_sales, global_sales) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Game game : games){
                pstmt.setInt(1, game.getRank());
                pstmt.setString(2, game.getName());
                pstmt.setString(3, game.getPlatform());
                pstmt.setInt(4, game.getYear());
                pstmt.setString(5, game.getGenre());
                pstmt.setString(6, game.getPublisher());
                pstmt.setDouble(7, game.getNaSales());
                pstmt.setDouble(8, game.getEuSales());
                pstmt.setDouble(9, game.getJpSales());
                pstmt.setDouble(10, game.getOtherSales());
                pstmt.setDouble(11, game.getGlobalSales());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
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
