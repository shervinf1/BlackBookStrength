package com.shervinf.blackbookstrength;

public class WeightInPOJO {
    private String date;
    private String weight;
    private String weightUnit;

    public WeightInPOJO() {
    }

    public WeightInPOJO(String date, String weight, String weightUnit) {
        this.date = date;
        this.weight = weight;
        this.weightUnit = weightUnit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }
}
