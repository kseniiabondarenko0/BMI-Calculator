package org.example.bmicalculatordemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Label;
import javafx.stage.Stage;

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

        bmiCategoryLabel.setText("Latest: " + String.format("%.2f", latestBmi) + "\tyour condition is: " + getBMICondition(latestBmi) );

    }

    @FXML
    protected void onBackClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    BMICalculatorApp.class.getResource("bmi_calculator_view.fxml")
            );
            Scene scene = new Scene(loader.load(), 339, 631);
            Stage stage = (Stage) bmiChart.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getBMICondition(double bmi){
        if(bmi<18.5){
            return "Underweight";
        }
        else if(bmi<25){
            return "Normal weight";
        }
        else if(bmi<30){
            return "Overweight";
        } else {
            return "Obese";
        }

    }





}
