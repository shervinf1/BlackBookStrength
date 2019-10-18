package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit1rmActivity extends AppCompatActivity {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_1rm);
        toolbarSetup();
        recyclerViewSetup();
        prepareData();
    }


    public void toolbarSetup(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.edit1rmToolbar);
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
        settings = new SettingsPOJO("Edit Deadlift 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Squat 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Bench 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit OHP 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        mAdapter.notifyDataSetChanged();
    }


    private void recyclerViewSetup() {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.edit1rmRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        show1rmNumberPicker(position);
                        break;
                    case 1:
                        show1rmNumberPicker(position);
                        break;
                    case 2:
                        show1rmNumberPicker(position);
                        break;
                    case 3:
                        show1rmNumberPicker(position);
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(Edit1rmActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }
    public void show1rmNumberPicker(int position) {
        //I define the dialog and I load the xml layout: custom_weight_goal_dialog_goal_dialog.xml into the view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View theView = inflater.inflate(R.layout.custom_1rm_dialog, null);

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
        integerPicker.setMaxValue(1000);
        integerPicker.setValue(200);

        String decimals[] = new String[20];
        for(int i = 0;i < 100; i+=5) {
            if( i < 10 )
                decimals[i/5] = "0"+i;
            else
                decimals[i/5] = ""+i;
        }
        decimalPicker.setDisplayedValues(decimals);

        decimalPicker.setMinValue(0);
        decimalPicker.setMaxValue(19);
        decimalPicker.setValue(0);

        builder.show();
    }
}
