package com.shervinf.blackbookstrength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Edit1rmActivity extends AppCompatActivity {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private DocumentReference newUserDocumentRef = db.collection("users").document(userID);
    private CollectionReference mainLiftCollectionReference;
    private static DecimalFormat df2 = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_1rm);
        toolbarSetup();
        recyclerViewSetup();
        prepareData();
    }

    //Method that displays back button in toolbar and ends this activity when button is clicked.
    public void toolbarSetup(){
        Toolbar mToolbar =findViewById(R.id.edit1rmToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }



    //Method that adds data into object array list type SettingsPOJO and display it in recycler view.
    private void prepareData() {
        SettingsPOJO settings;
        settings = new SettingsPOJO("Edit Deadlift 1RPM","Change one rep max for Deadlift");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Squat 1RPM","Change one rep max for Squat");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Bench 1RPM","Change one rep max for Bench");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit OHP 1RPM","Change one rep max for OHP");
        mArrayList.add(settings);
        mAdapter.notifyDataSetChanged();
    }



    //Method that find recycler view by the id and displays it.
    private void recyclerViewSetup() {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.edit1rmRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        customDialogBuilder(position);
                        break;
                    case 1:
                        customDialogBuilder(position);
                        break;
                    case 2:
                        customDialogBuilder(position);
                        break;
                    case 3:
                        customDialogBuilder(position);
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(Edit1rmActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }




    //Method to create Alert Dialog for changing weightGoal attribute
    //In Firebase cloud firestore database
    public void customDialogBuilder(final int position){
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_1rm_decimal_input_dialog, null);
        final EditText editText = dialogView.findViewById(R.id.edit_1rm_comment);
        Button buttonSubmit = dialogView.findViewById(R.id.buttonSubmit);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String attrbuteUpdate =null;
                switch (position){
                    case 0:
                        newUserDocumentRef
                            .update("deadliftMax",Double.parseDouble(editText.getText().toString()))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("BlackBookStrength","successfully updated maximum attribute");
                                    getCollectionDocuments(0);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("BlackBookStrength","Failed to update maximum attribute");
                                    e.printStackTrace();
                                }
                            });
                        dialogBuilder.dismiss();
                    break;
                    case 1:
                        newUserDocumentRef
                                .update("squatMax",Double.parseDouble(editText.getText().toString()))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("BlackBookStrength","successfully updated maximum attribute");
                                        getCollectionDocuments(1);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("BlackBookStrength","Failed to update maximum attribute");
                                        e.printStackTrace();
                                    }
                                });
                        dialogBuilder.dismiss();
                    break;
                    case 2:
                        newUserDocumentRef
                                .update("benchMax",Double.parseDouble(editText.getText().toString()))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("BlackBookStrength","successfully updated maximum attribute");
                                        getCollectionDocuments(2);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("BlackBookStrength","Failed to update maximum attribute");
                                        e.printStackTrace();
                                    }
                                });
                        dialogBuilder.dismiss();
                    break;
                    case 3:
                        newUserDocumentRef
                                .update("ohpMax",Double.parseDouble(editText.getText().toString()))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("BlackBookStrength","successfully updated maximum attribute");
                                        getCollectionDocuments(3);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("BlackBookStrength","Failed to update maximum attribute");
                                        e.printStackTrace();
                                    }
                                });
                        dialogBuilder.dismiss();
                    break;
                }
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }







    private void getCollectionDocuments(final int pos) {
        switch (pos){
            case 0:
                for (int i = 1;i < 5;i++) {
                    final int finalI = i;
                    db.collection("users").document(userID).collection("deadliftWeek"+i)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    ArrayList<String> documentID = new ArrayList<>();
                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                        documentID.add(documentSnapshot.getId());
                                    }

                                    deleteCollectionDocuments(documentID,"deadliftWeek" + finalI);
                                    prepareMainLiftData("deadliftWeek" + finalI);

                                    Log.d("getCollectionDocuments", "deleted document" + pos);
                                }
                            });
                }
                break;
            case 1:
                for (int i = 1;i < 5;i++) {
                    final int finalI = i;
                    db.collection("users").document(userID).collection("squatWeek"+i)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    ArrayList<String> documentID = new ArrayList<>();
                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                        documentID.add(documentSnapshot.getId());
                                    }

                                    deleteCollectionDocuments(documentID,"squatWeek" + finalI);
                                    prepareMainLiftData("squatWeek" + finalI);

                                    Log.d("getCollectionDocuments", "deleted document" + pos);
                                }
                            });
                }
                break;
            case 2:
                for (int i = 1;i < 5;i++) {
                    final int finalI = i;
                    db.collection("users").document(userID).collection("benchWeek"+i)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    ArrayList<String> documentID = new ArrayList<>();
                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                        documentID.add(documentSnapshot.getId());
                                    }

                                    deleteCollectionDocuments(documentID,"benchWeek" + finalI);
                                    prepareMainLiftData("benchWeek" + finalI);

                                    Log.d("getCollectionDocuments", "deleted document" + pos);
                                }
                            });
                }
                break;
            case 3:
                for (int i = 1;i < 5;i++) {
                    final int finalI = i;
                    db.collection("users").document(userID).collection("ohpWeek"+i)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    ArrayList<String> documentID = new ArrayList<>();
                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                        documentID.add(documentSnapshot.getId());
                                    }
                                    deleteCollectionDocuments(documentID,"ohpWeek" + finalI);
                                    prepareMainLiftData("ohpWeek" + finalI);
                                    Log.d("getCollectionDocuments", "deleted document" + pos);
                                }
                            });
                }
                break;
        }









    }
    public void prepareMainLiftData(final String colName){
        switch (colName) {
            case "deadliftWeek1":
                db.collection("users").document(userID).collection("deadliftWeek1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                    db.collection("users").document(userID).collection("deadliftWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                    db.collection("users").document(userID).collection("deadliftWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                    db.collection("users").document(userID).collection("deadliftWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                    db.collection("users").document(userID).collection("deadliftWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_65)), "lbs", 65, "% x 5 REPS", 4));
                                    db.collection("users").document(userID).collection("deadliftWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 5));
                                    db.collection("users").document(userID).collection("deadliftWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 5 REPS", 6));
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
            break;
            case "deadliftWeek2":
                db.collection("users").document(userID).collection("deadliftWeek2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("deadliftWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("deadliftWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("deadliftWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("deadliftWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_70)), "lbs", 70, "% x 3 REPS", 4));
                                        db.collection("users").document(userID).collection("deadliftWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_80)), "lbs", 80, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("deadliftWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_90)), "lbs", 90, "% x 3 REPS", 6));
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
                break;
            case "deadliftWeek3":
                db.collection("users").document(userID).collection("deadliftWeek3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("deadliftWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("deadliftWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("deadliftWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("deadliftWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("deadliftWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("deadliftWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_95)), "lbs", 95, "% x 1 REPS", 6));
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
                break;
            case "deadliftWeek4":
                db.collection("users").document(userID).collection("deadliftWeek4").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("deadliftWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("deadliftWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("deadliftWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("deadliftWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("deadliftWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 5));
                                        db.collection("users").document(userID).collection("deadliftWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 6));
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
                break;
            case "ohpWeek1":
                db.collection("users").document(userID).collection("ohpWeek1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("ohpWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("ohpWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("ohpWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("ohpWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_65)), "lbs", 65, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("ohpWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 5));
                                        db.collection("users").document(userID).collection("ohpWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 5 REPS", 6));
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
                break;
            case "ohpWeek2":
                db.collection("users").document(userID).collection("ohpWeek2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("ohpWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("ohpWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("ohpWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("ohpWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_70)), "lbs", 70, "% x 3 REPS", 4));
                                        db.collection("users").document(userID).collection("ohpWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_80)), "lbs", 80, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("ohpWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_90)), "lbs", 90, "% x 3 REPS", 6));
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
                break;
            case "ohpWeek3":
                db.collection("users").document(userID).collection("ohpWeek3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("ohpWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("ohpWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("ohpWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("ohpWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("ohpWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("ohpWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_95)), "lbs", 95, "% x 1 REPS", 6));
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
                break;
            case "ohpWeek4":
                db.collection("users").document(userID).collection("ohpWeek4").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("ohpWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("ohpWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("ohpWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("ohpWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("ohpWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 5));
                                        db.collection("users").document(userID).collection("ohpWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 6));
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
                break;
            case "benchWeek1":
                db.collection("users").document(userID).collection("benchWeek1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("benchWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("benchWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("benchWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("benchWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_65)), "lbs", 65, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("benchWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 5));
                                        db.collection("users").document(userID).collection("benchWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 5 REPS", 6));
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
                break;
            case "benchWeek2":
                db.collection("users").document(userID).collection("benchWeek2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("benchWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("benchWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("benchWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("benchWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_70)), "lbs", 70, "% x 3 REPS", 4));
                                        db.collection("users").document(userID).collection("benchWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_80)), "lbs", 80, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("benchWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_90)), "lbs", 90, "% x 3 REPS", 6));
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
                break;
            case "benchWeek3":
                mainLiftCollectionReference = db.collection("users").document(userID).collection("benchWeek3");
                db.collection("users").document(userID).collection("benchWeek3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("benchWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("benchWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("benchWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("benchWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("benchWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("benchWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_95)), "lbs", 95, "% x 1 REPS", 6));
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
                break;
            case "benchWeek4":
                db.collection("users").document(userID).collection("benchWeek4").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("benchWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("benchWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("benchWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("benchWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("benchWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 5));
                                        db.collection("users").document(userID).collection("benchWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 6));
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
                break;
            case "squatWeek1":
                db.collection("users").document(userID).collection("squatWeek1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("squatWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("squatWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("squatWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("squatWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_65)), "lbs", 65, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("squatWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 5));
                                        db.collection("users").document(userID).collection("squatWeek1").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 5 REPS", 6));
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
                break;
            case "squatWeek2":
                db.collection("users").document(userID).collection("squatWeek2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("squatWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("squatWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("squatWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("squatWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_70)), "lbs", 70, "% x 3 REPS", 4));
                                        db.collection("users").document(userID).collection("squatWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_80)), "lbs", 80, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("squatWeek2").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_90)), "lbs", 90, "% x 3 REPS", 6));
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
                break;
            case "squatWeek3":
                db.collection("users").document(userID).collection("squatWeek3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("squatWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("squatWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("squatWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("squatWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)), "lbs", 75, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("squatWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)), "lbs", 85, "% x 3 REPS", 5));
                                        db.collection("users").document(userID).collection("squatWeek3").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_95)), "lbs", 95, "% x 1 REPS", 6));
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
                break;
            case "squatWeek4":
                db.collection("users").document(userID).collection("squatWeek4").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                                        db.collection("users").document(userID).collection("squatWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 1));
                                        db.collection("users").document(userID).collection("squatWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 2));
                                        db.collection("users").document(userID).collection("squatWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 3));
                                        db.collection("users").document(userID).collection("squatWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)), "lbs", 40, "% x 5 REPS", 4));
                                        db.collection("users").document(userID).collection("squatWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)), "lbs", 50, "% x 5 REPS", 5));
                                        db.collection("users").document(userID).collection("squatWeek4").add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)), "lbs", 60, "% x 5 REPS", 6));
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
                break;
        }
    }



    private void deleteCollectionDocuments(ArrayList<String> list,  String colName){
        for (int i = 0; i < list.size();i++) {
            db.collection("users").document(userID).collection(colName).document(list.get(i)).delete();
        }

    }






}

