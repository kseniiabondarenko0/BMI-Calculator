package org.example.bmicalculatordemo;

import java.util.ArrayList;
import java.util.List;

public class AppState {
    private final List<BMIRecord> records = new ArrayList<>();
    private int measurementCounter = 0;

    public List<BMIRecord> getRecords() {
        return records;
    }

    public void addMeasurement(double bmi, double bmr) {
        measurementCounter++;
        records.add(new BMIRecord("№" + measurementCounter, bmi, bmr));
    }
}
