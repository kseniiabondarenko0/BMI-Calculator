package org.example.bmicalculatordemo;

public class BMIRecord {

    private final String label;
    private final double bmi;
    private final double bmr;

    public BMIRecord(String label, double bmi, double bmr) {
        this.label = label;
        this.bmi = bmi;
        this.bmr = bmr;
    }

    public String getLabel() {
        return label;
    }

    public double getBmi() {
        return bmi;
    }

    public double getBmr() {
        return bmr;
    }


}
