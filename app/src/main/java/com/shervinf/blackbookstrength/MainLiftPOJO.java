package com.shervinf.blackbookstrength;

import android.content.Intent;

import javax.sql.StatementEvent;

public class MainLiftPOJO {
    private Double weight;
    private String weightUnit;
    private Integer percentage;
    private String reps;



    //Default constructor
    public MainLiftPOJO() { }

    //Argument Constructor
    public MainLiftPOJO(Double weight, String weightUnit, Integer percentage, String reps) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.percentage = percentage;
        this.reps = reps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getReps() {
        return reps;
    }
    public void setReps(String reps) {
        this.reps = reps;
    }
}
