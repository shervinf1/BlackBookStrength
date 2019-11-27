package com.shervinf.blackbookstrength;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SquatActivityWeek3 extends AppCompatActivity {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference mainLiftCollectionReference = db.collection("users").document(userID).collection("squatWeek3");
    private MainLiftAdapter mainLiftAdapter;
    private static DecimalFormat df2 = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat);

        //Calling methods
        toolbarSetup();
        recyclerViewSetup();
        prepareData();
    }

    //Method that creates back navigation button and finishes this activity when pressed.
    public void toolbarSetup() {
        Toolbar mToolbar = findViewById(R.id.squatToolbar);
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

    public void prepareData(){
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
                                assert newUser != null;
                                double max = newUser.getSquatMax();
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_40)),"lbs",40, "% x 5 REPS",1));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_50)),"lbs",50, "% x 3 REPS",2));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_60)),"lbs",60, "% x 1 REPS",3));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_75)),"lbs",75, "% x 5 REPS",4));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_85)),"lbs",85, "% x 3 REPS",5));
                                mainLiftCollectionReference.add(new MainLiftPOJO(Double.parseDouble(df2.format(max * MainLiftPOJO.PERCENT_95)),"lbs",95, "% x 1 REPS",6));
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




    //    Method that find recycler view by the id and displays it.
    public void recyclerViewSetup(){
        Query query = mainLiftCollectionReference.orderBy("priority",Query.Direction.ASCENDING).limit(6);
        FirestoreRecyclerOptions<MainLiftPOJO> options = new FirestoreRecyclerOptions.Builder<MainLiftPOJO>()
                .setQuery(query, MainLiftPOJO.class)
                .build();
        mainLiftAdapter = new MainLiftAdapter(options);
        RecyclerView mRecyclerView = findViewById(R.id.squatRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
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