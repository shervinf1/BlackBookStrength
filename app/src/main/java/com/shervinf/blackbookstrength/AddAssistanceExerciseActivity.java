package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddAssistanceExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assistance_exercise);
        Intent intent = getIntent();
        String mainLiftType = intent.getStringExtra("mainLiftType");
        toolbarSetup(mainLiftType);
        fabSetup();
    }









    //Method that displays back button in toolbar and ends this activity when button is clicked.
    public void toolbarSetup(String type) {
        Toolbar mToolbar = findViewById(R.id.addAssistanceExerciseToolbar);
        TextView mToolbarTextView = findViewById(R.id.addAssistanceExerciseToolbarTextView);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbarTextView.setText(type + " Assitance Exercises");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }




    private void fabSetup(){
        FloatingActionButton fab;
        //Casting floating action button to respective ID
        fab = findViewById(R.id.add_assistance_exercise_action_button);
        //Determines whether the floating action button is null or not and then proceed to set the OnClickListener
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
    }
}