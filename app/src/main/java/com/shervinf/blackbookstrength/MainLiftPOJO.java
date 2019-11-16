package com.shervinf.blackbookstrength;

import android.content.Intent;

import javax.sql.StatementEvent;

public class MainLiftPOJO {
    private Double weight;
    private String weightUnit;
    private Integer percentage;
    private String reps;
    private boolean checked;
    public static final double PERCENT_40 = .40;
    public static final double PERCENT_50 = .50;
    public static final double PERCENT_60 = .60;
    public static final double PERCENT_65 = .65;
    public static final double PERCENT_70 = .70;
    public static final double PERCENT_75 = .75;
    public static final double PERCENT_80 = .80;
    public static final double PERCENT_85 = .85;
    public static final double PERCENT_90 = .90;
    public static final double PERCENT_95 = .95;





    //Default constructor
    public MainLiftPOJO() { }

    //Argument Constructor
    public MainLiftPOJO(Double weight, String weightUnit, Integer percentage, String reps) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.percentage = percentage;
        this.reps = reps;
        checked = false;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
