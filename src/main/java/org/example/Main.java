package org.example;

import java.util.List;

import static org.example.Parser.parse;

public class Main {
    public static void main(String[] args) {
        List<Game> games = parse();

        for (Game game : games) {
            System.out.println(game);
        }
    }
}