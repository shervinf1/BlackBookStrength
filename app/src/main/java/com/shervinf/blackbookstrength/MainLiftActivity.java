package com.shervinf.blackbookstrength;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainLiftActivity extends AppCompatActivity {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference mainLiftCollectionReference;
    private MainLiftAdapter mainLiftAdapter;
    private static DecimalFormat df2 = new DecimalFormat("#.##");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lift);


        Intent intent = getIntent();
        String collectionName = intent.getStringExtra("collectionName");
        String mainLiftName = intent.getStringExtra("mainLiftName");
        Double percentageDecimal = intent.getDoubleExtra("percentageDecimal",0);
        Integer percentageInteger = intent.getIntExtra("percentageInteger",0);
        String set1 = intent.getStringExtra("set1");
        String set2 = intent.getStringExtra("set2");
        String set3 = intent.getStringExtra("set3");
        mainLiftCollectionReference = db.collection("users").document(userID).collection(collectionName);

        if (mainLiftName.equals("Deadlift")){
            prepareDeadliftData(percentageDecimal, percentageInteger, set1, set2, set3);
        }
        else if (mainLiftName.equals("Squat")){
            prepareSquatData(percentageDecimal, percentageInteger, set1, set2, set3);
        }
        else if (mainLiftName.equals("Bench")){
            prepareBenchData(percentageDecimal, percentageInteger, set1, set2, set3);
        }
        else if (mainLiftName.equals("OHP")){
            prepareOHPData(percentageDecimal, percentageInteger, set1, set2, set3);
        }

        toolbarSetup(mainLiftName);
        recyclerViewSetup();
    }





    //Creating toolbar back button to return to previous activity
    public void toolbarSetup(String mlName) {
        Toolbar mToolbar = findViewById(R.id.mainLiftToolbar);
        TextView toolbarTextView = findViewById(R.id.mainLiftToolbarTextView);
        toolbarTextView.setText(mlName);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }




    public void prepareDeadliftData(final Double percentageD, final Integer percentageI, final String set1, final String set2, final String set3){
        mainLiftCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                DocumentReference docRef = db.collection("users").document(userID);
                if (queryDocumentSnapshots.isEmpty()) {
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                UserPOJO newUser = document.toObject(UserPOJO.class);
                                double max = newUser.getDeadliftMax();
                                Log.d("BlackBookStrength", "Deadlift max variable" + max);
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS",1));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS",2));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS",3));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD))), "lbs", percentageI, set1,4));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.1))), "lbs", percentageI +10, set2,5));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.2))), "lbs", percentageI +20, set3,6));
                                mainLiftAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void prepareBenchData(final Double percentageD, final Integer percentageI, final String set1, final String set2, final String set3){
        mainLiftCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                DocumentReference docRef = db.collection("users").document(userID);
                if (queryDocumentSnapshots.isEmpty()) {
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                UserPOJO newUser = document.toObject(UserPOJO.class);
                                double max = newUser.getBenchMax();
                                Log.d("BlackBookStrength", "Deadlift max variable" + max);
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS",1));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS",2));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS",3));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD))), "lbs", percentageI, set1,4));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.1))), "lbs", percentageI +10, set2,5));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.2))), "lbs", percentageI +20, set3,6));
                                mainLiftAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void prepareOHPData(final Double percentageD, final Integer percentageI, final String set1, final String set2, final String set3){
        mainLiftCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                DocumentReference docRef = db.collection("users").document(userID);
                if (queryDocumentSnapshots.isEmpty()) {
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                UserPOJO newUser = document.toObject(UserPOJO.class);
                                double max = newUser.getOhpMax();
                                Log.d("BlackBookStrength", "Deadlift max variable" + max);
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS",1));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS",2));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS",3));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD))), "lbs", percentageI, set1,4));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.1))), "lbs", percentageI +10, set2,5));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.2))), "lbs", percentageI +20, set3,6));
                                mainLiftAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }



    public void prepareSquatData(final Double percentageD, final Integer percentageI, final String set1, final String set2, final String set3){
        mainLiftCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                DocumentReference docRef = db.collection("users").document(userID);
                if (queryDocumentSnapshots.isEmpty()) {
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                UserPOJO newUser = document.toObject(UserPOJO.class);
                                double max = newUser.getSquatMax();
                                Log.d("BlackBookStrength", "Deadlift max variable" + max);
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS",1));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS",2));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS",3));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD))), "lbs", percentageI, set1,4));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.1))), "lbs", percentageI +10, set2,5));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * (percentageD +.2))), "lbs", percentageI +20, set3,6));
                                mainLiftAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }







    //Method that find recycler view by the id and displays it.
    public void recyclerViewSetup(){
        Query query = mainLiftCollectionReference.orderBy("priority",Query.Direction.ASCENDING).limit(6);
        FirestoreRecyclerOptions<MainLiftPOJO> options = new FirestoreRecyclerOptions.Builder<MainLiftPOJO>()
                .setQuery(query, MainLiftPOJO.class)
                .build();
        mainLiftAdapter = new MainLiftAdapter(options);
        RecyclerView mRecyclerView = findViewById(R.id.mainLiftRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView.setAdapter(mainLiftAdapter);
        mainLiftAdapter.setOnItemClickListener(new MainLiftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                MainLiftPOJO mainLift = documentSnapshot.toObject(MainLiftPOJO.class);
                boolean isChecked = mainLift.getChecked();
                if (!isChecked){
                    documentSnapshot.getReference().update("checked",true);
                }
                else{
                    documentSnapshot.getReference().update("checked",false);
                }
            }
        });
        Log.d("BlackBookStrength", "The application stopped after DeadLiftActivity.java");
    }





    @Override
    public void onStart() {
        super.onStart();
        mainLiftAdapter.startListening();
    }




    @Override
    public void onStop() {
        super.onStop();
        mainLiftAdapter.stopListening();
    }
}