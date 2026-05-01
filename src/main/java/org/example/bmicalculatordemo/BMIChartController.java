package org.example.bmicalculatordemo;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.lang.classfile.Label;
import java.util.List;

public class BMIChartController {


    @FXML
    private LineChart<String, Number> bmiChart;
    @FXML
    private Label bmiCategoryLabel;

    //setRecords() — called by BMICalculatorController right after loading this
    public void setRecords(List<BMIRecord> records) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.setName("BMI");   // appears in the chart legend

        // Loop over every saved BMIRecord and add it as one point on the line.
        for (BMIRecord record :records) {
            // XYChart.Data pairs one X value with one Y value (the BMI number).
            series.getData().add(
                    new XYChart.Data<>(record.getLabel(), record.getBmi())
            );
        }

        bmiChart.getData().add(series);
        // Show the category for the most recent measurement (last in the list).
        double latestBmi = records.get(records.size() - 1).getBmi();
        //bmiCategoryLabel.setText();

    }



}
