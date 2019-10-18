package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class EditGoalsActivity extends AppCompatActivity {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goals);
        toolbarSetup();
        recyclerViewSetup();
        prepareData();
    }


    public void toolbarSetup() {
        Toolbar mToolbar = findViewById(R.id.editGoalsToolbar);
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
        settings = new SettingsPOJO("Edit Weight Goal", "Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Calorie Goal", "Settings Sub Label");
        mArrayList.add(settings);

        mAdapter.notifyDataSetChanged();
    }


    private void recyclerViewSetup() {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.editGoalsRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch (position) {
                    case 0:
                        Log.d("BlackBook Strength", "Stopped after OnSettingsViewItemsClicked()" + position);
                        showWeightNumberPicker();
                        break;
                    case 1:
                        Log.d("BlackBook Strength", "Stopped after OnSettingsViewItemsClicked()" + position);
                        showCalorieNumberPicker();
                        break;

                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(EditGoalsActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }


    public void showWeightNumberPicker() {
        //I define the dialog and I load the xml layout: custom_weight_goal_dialog_goal_dialog.xml into the view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View theView = inflater.inflate(R.layout.custom_weight_goal_dialog, null);

        final NumberPicker integerPicker = theView.findViewById(R.id.integer_picker);
        final NumberPicker decimalPicker = theView.findViewById(R.id.decimal_picker);
        // I keep a reference to the 2 picker, in order to read their properties for later use

        builder.setView(theView)
                .setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("DBG","Price is: "+integerPicker.getValue() + "."+decimalPicker.getValue());
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        integerPicker.setMinValue(0);
        integerPicker.setMaxValue(500);
        integerPicker.setValue(200);

        decimalPicker.setMinValue(0);
        decimalPicker.setMaxValue(9);
        decimalPicker.setValue(0);

        builder.show();
    }




    public void showCalorieNumberPicker() {
        //I define the dialog and I load the xml layout: custom_weight_goal_dialog_goal_dialog.xml into the view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View theView = inflater.inflate(R.layout.custom_calorie_goal_dialog, null);

        final NumberPicker caloriePicker = theView.findViewById(R.id.calorie_picker);
        // I keep a reference to the 2 picker, in order to read their properties for later use

        builder.setView(theView)
                .setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("DBG","Price is: "+caloriePicker.getValue());
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        caloriePicker.setMinValue(0);
        caloriePicker.setMaxValue(5000);
        caloriePicker.setValue(2000);
        builder.show();
    }

}
