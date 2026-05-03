package org.example.bmicalculatordemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class BMICalculatorController {
    //Controller = logic for scene 1

    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;

    @FXML private TextField weightField;
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

    private int measurementCount = 0;



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
    protected void onCalculateClick(){
        try{
            // .replace() - to exclude if we write with , or .
            double weight = Double.parseDouble(weightField.getText().replace(",", "."));
            double height = Double.parseDouble(heightField.getText().replace(",", "."));
            int age = Integer.parseInt(ageField.getText());


            if(weight < 0 || height < 0 || age < 0) {
                showAlert("Invalid Input", "Weight, height and age must be positive numbers.");
                return;
            }

            double bmi = calculateBMI(weight, height); // was with wrong order
            double bmr = calculateBMR(height, weight, age);

            //IntelliJ proposed, nice format with 2 decimal digits
            bmiResultLabel.setText("BMI: " + String.format("%.2f", bmi));
            bmrResultLabel.setText("BMR: " + String.format("%.2f", bmr));

            measurementCount++;
            String label = "№" +  measurementCount;
            records.add(new BMIRecord(label, bmi, bmr));



        }catch (NumberFormatException e){
            showAlert("Invalid Input", "Please enter valid numeric values");
        }
    }

    @FXML
    protected void onShowChartClick(){

        if(records.isEmpty()){
            showAlert("No data", "Please fill up data, at least one record");
            return;
        }


        try{
            //reads the FXML file and builds all the UI nodes described in it
            FXMLLoader loader = new FXMLLoader(BMICalculatorApp.class.getResource("bmi_chart.fxml"));

            Scene chartScene = new Scene(loader.load(), 400, 400);

            //Create the controller
            BMIChartController chartController = loader.getController();
            chartController.setRecords(records);

            Stage stage = (Stage) bmiResultLabel.getScene().getWindow();

            stage.setScene(chartScene);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private double calculateBMI(double weight, double heightInCm){

        // bmi = weight / (height^2 but in m)
        // IMPORTANT, now height is in cm

        // had strange logic before, better to separate convertion to cm into two different parts
        double heightInMeters = heightInCm / 100.0;
        return weight / (heightInMeters * heightInMeters);
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
