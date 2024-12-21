package org.example;

public class Game {
    private int rank;
    private String name;
    private String platform;
    private int year;
    private String genre;
    private String publisher;
    private double naSales;
    private double euSales;
    private double jpSales;
    private double otherSales;
    private double globalSales;

    public Game(int rank, String name, String platform, int year, String genre, String publisher,
                double naSales, double euSales, double jpSales, double otherSales, double globalSales) {
        this.rank = rank;
        this.name = name;
        this.platform = platform;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
        this.naSales = naSales;
        this.euSales = euSales;
        this.jpSales = jpSales;
        this.otherSales = otherSales;
        this.globalSales = globalSales;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getNaSales() {
        return naSales;
    }

    public double getEuSales() {
        return euSales;
    }

    public double getJpSales() {
        return jpSales;
    }

    public double getOtherSales() {
        return otherSales;
    }

    public double getGlobalSales() {
        return globalSales;
    }

    @Override
    public String toString() {
        return String.format("Game{rank=%d, name='%s', platform='%s', year=%d, genre='%s', publisher='%s', " +
                        "naSales=%.2f, euSales=%.2f, jpSales=%.2f, otherSales=%.2f, globalSales=%.2f}",
                rank, name, platform, year, genre, publisher, naSales, euSales, jpSales, otherSales, globalSales);
    }
}
