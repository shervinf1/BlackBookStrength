package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class EditAssistanceExerciseActivity extends AppCompatActivity {
    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assistance_exercise);

        toolbarSetup();
        recyclerViewSetup();
        prepareData();

    }






    //Method that displays back button in toolbar and ends this activity when button is clicked.
    public void toolbarSetup() {
        Toolbar mToolbar = findViewById(R.id.editAssistanceExerciseToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }







    private void prepareData() {
        SettingsPOJO settings = null;
        settings = new SettingsPOJO("Deadlift","Add assistance exercise to Deadlifts");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Bench","Add assistance exercise to Bench");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Squats","Add assistance exercise to Squats");
        mArrayList.add(settings);
        settings = new SettingsPOJO("OHP","Add assistance exercise to OHP");
        mArrayList.add(settings);
        mAdapter.notifyDataSetChanged();
    }




    private void recyclerViewSetup() {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.editAssistanceExerciseRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Intent addAssistantExerciseDeadliftIntent = new Intent(getApplicationContext(), AddAssistanceExerciseActivity.class);
                        addAssistantExerciseDeadliftIntent.putExtra("mainLiftType", "Deadlift");
                        addAssistantExerciseDeadliftIntent.putExtra("collectionName", "deadliftWeek");
                        finish();
                        startActivity(addAssistantExerciseDeadliftIntent);
                        break;
                    case 1:
                        Intent addAssistantExerciseBenchIntent = new Intent(getApplicationContext(), AddAssistanceExerciseActivity.class);
                        addAssistantExerciseBenchIntent.putExtra("mainLiftType", "Bench");
                        addAssistantExerciseBenchIntent.putExtra("collectionName", "benchWeek");
                        finish();
                        startActivity(addAssistantExerciseBenchIntent);
                        break;
                    case 2:
                        Intent addAssistantExerciseSquatIntent = new Intent(getApplicationContext(), AddAssistanceExerciseActivity.class);
                        addAssistantExerciseSquatIntent.putExtra("mainLiftType", "Squat");
                        addAssistantExerciseSquatIntent.putExtra("collectionName", "squatWeek");
                        finish();
                        startActivity(addAssistantExerciseSquatIntent);
                        break;
                    case 3:
                        Intent addAssistantExerciseOHPIntent = new Intent(getApplicationContext(), AddAssistanceExerciseActivity.class);
                        addAssistantExerciseOHPIntent.putExtra("mainLiftType", "OHP");
                        addAssistantExerciseOHPIntent.putExtra("collectionName", "ohpWeek");
                        finish();
                        startActivity(addAssistantExerciseOHPIntent);
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.setAdapter(mAdapter);
    }
}