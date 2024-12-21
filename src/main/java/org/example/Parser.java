package org.example;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Game> parse() {
        List<Game> games = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader("game.csv"))) {
            String[] line;
            boolean isHeader = true;

            while ((line = csvReader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                Game game = new Game(
                        parseInt(line[0]),
                        line[1],
                        line[2],
                        parseInt(line[3]),
                        line[4],
                        line[5],
                        parseDouble(line[6]),
                        parseDouble(line[7]),
                        parseDouble(line[8]),
                        parseDouble(line[9]),
                        parseDouble(line[10])
                );

                games.add(game);
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return games;
    }

    private static int parseInt(String value) {
        if (value == null || value.equalsIgnoreCase("N/A")) {
            return 0;
        }
        return Integer.parseInt(value.trim());
    }

    private static double parseDouble(String value) {
        if (value == null || value.equalsIgnoreCase("N/A")) {
            return 0.0;
        }
        return Double.parseDouble(value.trim());
    }
}
