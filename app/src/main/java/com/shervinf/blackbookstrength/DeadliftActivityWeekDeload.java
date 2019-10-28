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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class DeadliftActivityWeekDeload extends AppCompatActivity {

    private ArrayList<MainLiftPOJO> mArrayList = new ArrayList<>();
    private MainLiftAdapter mAdapter;

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
                    MainLiftPOJO Lift;
                    Lift = new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_40,"lbs", 40, "% x 5 REPS");
                    mArrayList.add(Lift);
                    Lift = new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_50,"lbs",50, "% x 5 REPS");
                    mArrayList.add(Lift);
                    Lift = new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_60,"lbs",60, "% x 5 REPS");
                    mArrayList.add(Lift);
                    Lift = new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_40,"lbs",40, "% x 5 REPS");
                    mArrayList.add(Lift);
                    Lift = new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_50,"lbs",50, "% x 5 REPS");
                    mArrayList.add(Lift);
                    Lift = new MainLiftPOJO(deadliftMax * MainLiftPOJO.PERCENT_60,"lbs",60, "% x 5 REPS");
                    mArrayList.add(Lift);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Log.d("BlackBookStrength", "Current data: null");
                }
            }
        });
    }




    //Method that find recycler view by the id and displays it.
    private void recyclerViewSetup(){
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.deadliftRecyclerView);
        mAdapter = new MainLiftAdapter(mArrayList, new OnMainLiftClickListener() {
            @Override
            public void onMainLiftViewItemClicked(int position, int id) {
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
        Log.d("BlackBookStrength", "The application stopped after DeadLiftActivity.java");
    }


}
