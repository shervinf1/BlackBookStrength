package com.shervinf.blackbookstrength;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class DeadliftActivityWeek2 extends AppCompatActivity {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference mainLiftCollectionReference = db.collection("users").document(userID).collection("deadliftWeek2");
    private MainLiftAdapter mainLiftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlift);

        toolbarSetup();
        recyclerViewSetup();
        prepareData();
    }



    public void toolbarSetup() {
        Toolbar mToolbar = findViewById(R.id.deadliftToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }




    private void prepareData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DocumentReference docRef = db.collection("users").document(userID);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("BlackBookStrength", "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    UserPOJO newUser = snapshot.toObject(UserPOJO.class);
                    Double deadliftMax = newUser.getDeadliftMax();
                    mainLiftCollectionReference.add(new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_40,"lbs", 40, "% x 3 REPS"));
                    mainLiftCollectionReference.add(new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_50,"lbs",50, "% x 3 REPS"));
                    mainLiftCollectionReference.add(new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_60,"lbs",60, "% x 3 REPS"));
                    mainLiftCollectionReference.add(new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_70,"lbs",70, "% x 3 REPS"));
                    mainLiftCollectionReference.add(new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_80,"lbs",80, "% x 3 REPS"));
                    mainLiftCollectionReference.add(new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_90,"lbs",90, "% x 3 REPS"));
                    mainLiftAdapter.notifyDataSetChanged();
                } else {
                    Log.d("BlackBookStrength", "Current data: null");
                }
            }
        });
    }




    //Method that find recycler view by the id and displays it.
    private void recyclerViewSetup(){
        Query query = mainLiftCollectionReference.orderBy("percentage",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<MainLiftPOJO> options = new FirestoreRecyclerOptions.Builder<MainLiftPOJO>()
                .setQuery(query, MainLiftPOJO.class)
                .build();
        mainLiftAdapter = new MainLiftAdapter(options);
        RecyclerView mRecyclerView = findViewById(R.id.deadliftRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mainLiftAdapter);
        Log.d("BlackBookStrength", "The application stopped after DeadLiftActivity.java");
    }


}
