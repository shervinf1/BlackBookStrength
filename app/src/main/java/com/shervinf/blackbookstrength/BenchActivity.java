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

public class BenchActivity extends AppCompatActivity {
    private ArrayList<MainLiftPOJO> mArrayList = new ArrayList<>();
    private MainLiftAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bench);
        RecyclerView mRecyclerView1;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.benchToolbar);
        setSupportActionBar(myToolbar);

        mRecyclerView1 = (RecyclerView) findViewById(R.id.benchRecyclerView);


        mAdapter = new MainLiftAdapter(mArrayList);

        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
        Log.d("debugMode", "The application stopped after SquatActivity.java");
        startingLifts();

    }

    private void startingLifts() {
        MainLiftPOJO Lift = null;
        Lift = new MainLiftPOJO(100, "lbs", 40, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(130, "lbs", 50, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(160, "lbs", 60, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(100, "lbs", 65, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(130, "lbs", 75, "% x 3 REPS");
        mArrayList.add(Lift);
        Lift = new MainLiftPOJO(180, "lbs", 85, "% x 3 REPS");
        mArrayList.add(Lift);

        mAdapter.notifyDataSetChanged();
    }
}