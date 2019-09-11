package com.shervinf.blackbookstrength;

public class ExercisePOJO {
    private String mName; //Name of the exercise
//    private String mSets; //How many sets of such exercise will be performed
//    private String mReps; //How many reps of such exercise will be performed

    public ExercisePOJO() {
    }

    public ExercisePOJO(String mName) { //public ExercisePOJO(String mName, String mSets, String mReps)
        this.mName = mName;
//        this.mSets = mSets;
//        this.mReps = mReps;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

//    public String getmSets() {
//        return mSets;
//    }
//
//    public void setmSets(String mSets) {
//        this.mSets = mSets;
//    }
//
//    public String getmReps() {
//        return mReps;
//    }
//
//    public void setmReps(String mReps) {
//        this.mReps = mReps;
//    }
}
