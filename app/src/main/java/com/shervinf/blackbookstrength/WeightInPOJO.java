package com.shervinf.blackbookstrength;

import com.google.firebase.firestore.ServerTimestamp;

import java.time.LocalDate;
import java.util.Date;

public class WeightInPOJO {
    private java.util.Date timeStamp;
    private String weight;
    private String weightUnit;

    public WeightInPOJO() {
    }

    public WeightInPOJO(String weight, String weightUnit,java.util.Date timeStamp) {
        this.timeStamp = timeStamp;
        this.weight = weight;
        this.weightUnit = weightUnit;
    }

    public java.util.Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(java.util.Date timeStamp) {
        this.timeStamp = timeStamp;
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
