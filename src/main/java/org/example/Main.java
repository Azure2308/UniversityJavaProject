package org.example;

import java.util.List;

import static org.example.Parser.parse;

public class Main {
    public static void main(String[] args) {
        List<Game> games = parse();
        Database database = new Database();
        database.createTables();

        //database.clearTables();

        /*for (Game game : games) {
            database.insertGame(
                    game.getRank(),
                    game.getName(),
                    game.getPlatform(),
                    game.getYear(),
                    game.getGenre(),
                    game.getPublisher(),
                    game.getNaSales(),
                    game.getEuSales(),
                    game.getJpSales(),
                    game.getOtherSales(),
                    game.getGlobalSales()
            );
        }*/

        database.closeConnection();
    }
}