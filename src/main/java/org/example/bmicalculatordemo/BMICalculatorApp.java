package org.example.bmicalculatordemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BMICalculatorApp  extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(BMICalculatorApp.class.getResource("bmi_calculator_view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 400, 420);
        // Configure and show the window.
        stage.setTitle("BMI and BMR Calculator");
        stage.setScene(scene);
        stage.show();  // makes the window visible smth similar to Python
    }

    public static void main(String[] args) {
        launch();
    }
}
