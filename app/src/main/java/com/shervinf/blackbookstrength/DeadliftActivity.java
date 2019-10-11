package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

import static com.shervinf.blackbookstrength.R.integer.week4_set1;

public class DeadliftActivity extends AppCompatActivity {

    private ArrayList<MainLiftPOJO> mArrayList = new ArrayList<>();
    private MainLiftAdapter mAdapter;
    private Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlift);

        recyclerViewSetup();
        startingLifts();

    }
    private void startingLifts() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserPOJO newUser = documentSnapshot.toObject(UserPOJO.class);
                Double deadliftMax = newUser.getDeadliftMax();


                MainLiftPOJO Lift = null;
                Lift = new MainLiftPOJO(deadliftMax,"lbs", 40, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(deadliftMax,"lbs",50, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(deadliftMax,"lbs",60, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(400.00,"lbs",65, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(430.00,"lbs",75, "% x 3 REPS");
                mArrayList.add(Lift);
                Lift = new MainLiftPOJO(480.00,"lbs",85, "% x 3 REPS");
                mArrayList.add(Lift);

                mAdapter.notifyDataSetChanged();
            }
        });



    }

    private void recyclerViewSetup(){
        RecyclerView mRecyclerView1;
        mRecyclerView1 = (RecyclerView) findViewById(R.id.deadliftRecyclerView);
        mAdapter = new MainLiftAdapter(mArrayList);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
        Log.d("debugMode", "The application stopped after DeadLiftActivity.java");
    }
    private void retrieveInfo(){


    }
}
