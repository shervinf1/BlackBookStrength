package com.shervinf.blackbookstrength;

import android.content.Intent;

import javax.sql.StatementEvent;

public class MainLift {
    private Integer weight;
    private String weightUnit;
    private Integer percentage;
    private String reps;



    //Default constructor
    public MainLift() { }

    //Argument Constructor
    public MainLift(Integer weight, String weightUnit, Integer percentage, String reps) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.percentage = percentage;
        this.reps = reps;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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
