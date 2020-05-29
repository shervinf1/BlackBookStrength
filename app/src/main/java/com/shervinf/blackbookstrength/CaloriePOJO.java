package com.shervinf.blackbookstrength;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class CaloriePOJO {
    private String calorie;
    private String calorieUnit;
    private java.util.Date timeStamp;

    public CaloriePOJO() {
    }

    public CaloriePOJO(String calorie, String calorieUnit, java.util.Date timeStamp) {
        this.calorie = calorie;
        this.calorieUnit = calorieUnit;
        this.timeStamp = timeStamp;
    }


    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getCalorieUnit() {
        return calorieUnit;
    }

    public void setCalorieUnit(String calorieUnit) {
        this.calorieUnit = calorieUnit;
    }


    public java.util.Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(java.util.Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
