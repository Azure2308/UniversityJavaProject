package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Graph {
    public static void createBarChart(Map<String, Double> data, String title) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Средние продажи", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                "Платформа",
                "Средние продажи (млн)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(79, 129, 189));

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        Font titleFont = new Font("Arial", Font.BOLD, 18);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        barChart.setTitle(new TextTitle(title, titleFont));
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setLabelFont(labelFont);

        plot.getDomainAxis().setCategoryMargin(0.2);

        TextTitle subtitle = new TextTitle("Данные взяты из games_database",
                new Font("Arial", Font.ITALIC, 12));
        subtitle.setPosition(RectangleEdge.BOTTOM);
        subtitle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        barChart.addSubtitle(subtitle);

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 800);

        ChartPanel chartPanel = new ChartPanel(barChart);
        frame.add(chartPanel);

        frame.setVisible(true);
    }
}
