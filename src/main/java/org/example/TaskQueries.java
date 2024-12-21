package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TaskQueries {
    private final Connection connection;

    public TaskQueries(Connection connection) {
        this.connection = connection;
    }

    // 1. Средние глобальные продажи по платформам
    public Map<String, Double> getAverageGlobalSalesByPlatform() {
        String query = "SELECT platform, AVG(global_sales) AS avg_sales " +
                "FROM games " +
                "GROUP BY platform " +
                "ORDER BY avg_sales DESC";

        Map<String, Double> graphicData = new HashMap<>();

        try (Statement stmt = connection.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            System.out.println("\n1) Средние глобальные продажи по платформам:");
            while (result.next()) {
                String platform = result.getString("platform");
                double avgSales = result.getDouble("avg_sales");
                graphicData.put(platform, avgSales);
                System.out.printf("Платформа: %s, Средние продажи: %.2f%n", platform, avgSales);
            }
        } catch (SQLException e) {
            System.err.println("\n1) Ошибка при выполнении запроса: " + e.getMessage());
        }
        return graphicData;
    }

    // 2. Игра с самым высоким показателем продаж в Европе
    public void getTopSellingGameInEurope2000() {
        String query = "SELECT name, eu_sales " +
                "FROM games " +
                "WHERE year = 2000 " +
                "ORDER BY eu_sales DESC " +
                "LIMIT 1";

        try (Statement stmt = connection.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            if (result.next()) {
                String name = result.getString("name");
                double euSales = result.getDouble("eu_sales");
                System.out.printf("\n2) Игра с наибольшими продажами в Европе за 2000 год: %s (%.2f млн)%n", name, euSales);
            } else {
                System.out.println("\n2) Нет данных за 2000 год.");
            }
        } catch (SQLException e) {
            System.err.println("\n2) Ошибка при выполнении запроса: " + e.getMessage());
        }
    }

    // 3. Самая продаваемая спортивная игра в Японии (2000-2006)
    public void getTopSportsGameInJapan2000to2006() {
        String query = "SELECT name, jp_sales " +
                "FROM games " +
                "WHERE year BETWEEN 2000 AND 2006 AND genre = 'Sports' " +
                "ORDER BY jp_sales DESC " +
                "LIMIT 1";

        try (Statement stmt = connection.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            if (result.next()) {
                String name = result.getString("name");
                double jpSales = result.getDouble("jp_sales");
                System.out.printf("\n3) Лучшая спортивная игра в Японии (2000-2006): %s (%.2f млн)%n", name, jpSales);
            } else {
                System.out.println("\n3) Нет данных по спортивным играм за 2000-2006 годы.");
            }
        } catch (SQLException e) {
            System.err.println("\n3) Ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}
