package com.shervinf.blackbookstrength;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class UserPOJO {
    private String userID;
    private @ServerTimestamp Date timeStamp;
    private Double deadliftMax;
    private Double squatMax;
    private Double benchMax;
    private Double ohpMax;
    private Integer calorieGoal;
    private Double weightGoal;



    public UserPOJO() {
    }

    public UserPOJO(String userID, Date timeStamp, Double deadliftMax, Double squatMax, Double benchMax, Double ohpMax, Integer calorieGoal, Double weightGoal) {
        this.userID = userID;
        this.timeStamp = timeStamp;
        this.deadliftMax = deadliftMax;
        this.squatMax = squatMax;
        this.benchMax = benchMax;
        this.ohpMax = ohpMax;
        this.calorieGoal = calorieGoal;
        this.weightGoal = weightGoal;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public java.util.Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(java.util.Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getDeadliftMax() {
        return deadliftMax;
    }

    public void setDeadliftMax(Double deadliftMax) {
        this.deadliftMax = deadliftMax;
    }

    public Double getSquatMax() {
        return squatMax;
    }

    public void setSquatMax(Double squatMax) {
        this.squatMax = squatMax;
    }

    public Double getBenchMax() {
        return benchMax;
    }

    public void setBenchMax(Double benchMax) {
        this.benchMax = benchMax;
    }

    public Double getOhpMax() {
        return ohpMax;
    }

    public void setOhpMax(Double ohpMax) {
        this.ohpMax = ohpMax;
    }
    public Integer getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(Integer calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public Double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }

}
