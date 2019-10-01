package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

public class DeadliftActivity extends AppCompatActivity {

    private ArrayList<MainLiftPOJO> mArrayList = new ArrayList<>();
    private MainLiftAdapter mAdapter;
    private Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlift);



        RecyclerView mRecyclerView1;
        mRecyclerView1 = (RecyclerView) findViewById(R.id.deadliftRecyclerView);
        mAdapter = new MainLiftAdapter(mArrayList);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
        Log.d("debugMode", "The application stopped after DeadLiftActivity.java");
        startingLifts();

    }
    private void startingLifts() {
        MainLiftPOJO Lift = null;
        Lift = new MainLiftPOJO(300,"lbs",40, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(330,"lbs",50, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(360,"lbs",60, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(400,"lbs",65, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(430,"lbs",75, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(480,"lbs",85, "% x 3 REPS");
        mArrayList.add(Lift);

        mAdapter.notifyDataSetChanged();
    }
}
