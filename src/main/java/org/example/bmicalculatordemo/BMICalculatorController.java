package org.example.bmicalculatordemo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BMICalculatorController {
    //Controller = logic for scene 1

    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private TextField bmiField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField ageField;

    @FXML
    private Label bmiResultLabel;
    @FXML
    private Label bmrResultLabel;


    // selecting one button automatically deselects the other, по определению
    private ToggleGroup genderGroup;



    private final List<BMIRecord> records= new ArrayList<>();


    @FXML
    private void initialize() {
        //ToggleGroup - only one element can be selected at a time
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        // male will be selected by a default
        maleRadio.setSelected(true);
    }

    @FXML
    private void onCalculateClick(){
        try{
            // .replace() - to exclude if we write with , or .
            double weight = Double.parseDouble(bmiField.getText().replace(",", "."));
            double height = Double.parseDouble(heightField.getText().replace(",", "."));
            int age = Integer.parseInt(ageField.getText());


            if(weight < 0 || height < 0 || age < 0) {
                showAlert("Invalid Input", "Weight, height and age must be positive numbers.");
                return;
            }

            double bmi = calculateBMI(height, weight);
            double bmr = calculateBMR(height, weight, age);



        }catch (NumberFormatException e){
            showAlert("Invalid Input", "Please enter valid numeric values");
        }
    }



    private double calculateBMI(double weight, double height){

        // bmi = weight / (height^2 but in m)
        // IMPORTANT, now height is in cm
        double bmi = weight / (height * height*100*100);
        return bmi;

    }

    private double calculateBMR(double weight, double height, int age){
        //Men: \((10 \times \text{weight in kg}) + (6.25 \times \text{height in cm}) - (5 \times \text{age in years}) + 5\)
        // Women: \((10 \times \text{weight in kg}) + (6.25 \times \text{height in cm}) - (5 \times \text{age in years}) - 161\)
        //Technologia!
        if(maleRadio.isSelected()){
            return 10*weight + 6.25*height - 5*age + 5;
        }else{
            return 10*weight + 6.25*height - 5*age - 161;
        }
    }

    private void showAlert(String title, String message){

        // IntelliJ helped
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
