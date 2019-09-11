package com.shervinf.blackbookstrength;

public class CaloriePOJO {
    private String calorie;
    private String calorieUnit;
    private String date;

    public CaloriePOJO() {
    }

    public CaloriePOJO(String date, String calorie, String calorieUnit) {
        this.calorie = calorie;
        this.calorieUnit = calorieUnit;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
