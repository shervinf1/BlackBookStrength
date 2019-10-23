package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SquatActivity extends AppCompatActivity {

    private ArrayList<MainLiftPOJO> mArrayList = new ArrayList<>();
    private MainLiftAdapter mAdapter;

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
    private void prepareData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserPOJO newUser = documentSnapshot.toObject(UserPOJO.class);
                Double squatMax = newUser.getSquatMax();
                MainLiftPOJO Lift;
                Lift = new MainLiftPOJO(squatMax * 0.40, "lbs", 40, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(squatMax * 0.50, "lbs", 50, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(squatMax * 0.60, "lbs", 60, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(squatMax * 0.65, "lbs", 65, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(squatMax * 0.75, "lbs", 75, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(squatMax * 0.85, "lbs", 85, "% x 3 REPS");
                mArrayList.add(Lift);
                mAdapter.notifyDataSetChanged();
            }
        });
    }




    //Method that find recycler view by the id and displays it.
    public void recyclerViewSetup(){
        RecyclerView mRecyclerView1;
        mRecyclerView1 = (RecyclerView) findViewById(R.id.squatRecyclerView);
        mAdapter = new MainLiftAdapter(mArrayList, new OnMainLiftClickListener() {
            @Override
            public void onMainLiftViewItemClicked(int position, int id) {
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
        Log.d("debugMode", "The application stopped after SquatActivity.java");
    }
}