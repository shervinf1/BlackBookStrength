package com.shervinf.blackbookstrength;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

public class SquatActivity extends AppCompatActivity {

    private ArrayList<MainLift> mArrayList = new ArrayList<>();
    private MainLiftAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat);
        RecyclerView mRecyclerView1;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.squatToolbar);
        setSupportActionBar(myToolbar);


        mRecyclerView1 = (RecyclerView) findViewById(R.id.squatRecyclerView);
        mAdapter = new MainLiftAdapter(mArrayList);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
        Log.d("debugMode", "The application stopped after SquatActivity.java");
        startingLifts();

    }

    private void startingLifts() {
        MainLift Lift = null;
        Lift = new MainLift(200, "lbs", 40, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLift(230, "lbs", 50, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLift(260, "lbs", 60, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLift(300, "lbs", 65, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLift(330, "lbs", 75, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLift(380, "lbs", 85, "% x 3 REPS");
        mArrayList.add(Lift);

        mAdapter.notifyDataSetChanged();
    }
}