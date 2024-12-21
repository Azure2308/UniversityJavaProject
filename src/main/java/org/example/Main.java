package org.example;

import java.util.List;
import java.util.Map;

import static org.example.Parser.parse;

public class Main {
    public static void main(String[] args) {
        List<Game> games = parse();
        Database database = new Database();
        database.createTables();
        //database.clearTables();
        //database.insertGame(games);

        TaskQueries queries = new TaskQueries(database.getConnection());
        Map<String, Double> data = queries.getAverageGlobalSalesByPlatform();
        Graph.createBarChart(data, "Средние глобальные продажи по платформам");
        queries.getTopSellingGameInEurope2000();
        queries.getTopSportsGameInJapan2000to2006();

        database.closeConnection();
    }
}