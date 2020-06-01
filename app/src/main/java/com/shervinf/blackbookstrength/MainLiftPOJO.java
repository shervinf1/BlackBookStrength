package com.shervinf.blackbookstrength;

import android.content.Intent;

import javax.sql.StatementEvent;

public class MainLiftPOJO {
    private String weight;
    private String weightUnit;
    private String percentage;
    private String reps;
    private boolean checked;
    private Integer priority;
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
    public MainLiftPOJO(String weight, String weightUnit, String percentage, String reps, Integer priority) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.percentage = percentage;
        this.reps = reps;
        this.priority = priority;
        checked = false;
    }

    public MainLiftPOJO(String weight, String rep, Integer priority){

    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }
}
