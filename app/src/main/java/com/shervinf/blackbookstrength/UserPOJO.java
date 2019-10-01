package com.shervinf.blackbookstrength;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class UserPOJO {
    private String userID;
    private @ServerTimestamp Date timeStamp;
    private Double deadliftMax;
    private Double squatMax;
    private Double benchMax;
    private Double OHPMax;
    private Integer calorieGoal;
    private Integer weightGoal;

    public Integer getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(Integer calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public Integer getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(Integer weightGoal) {
        this.weightGoal = weightGoal;
    }

    public UserPOJO() {
    }

    public UserPOJO(String userID, Date timeStamp, Double deadliftMax, Double squatMax, Double benchMax, Double OHPMax, Integer calorieGoal, Integer weightGoal) {
        this.userID = userID;
        this.timeStamp = timeStamp;
        this.deadliftMax = deadliftMax;
        this.squatMax = squatMax;
        this.benchMax = benchMax;
        this.OHPMax = OHPMax;
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

    public Double getOHPMax() {
        return OHPMax;
    }

    public void setOHPMax(Double OHPMax) {
        this.OHPMax = OHPMax;
    }
}
