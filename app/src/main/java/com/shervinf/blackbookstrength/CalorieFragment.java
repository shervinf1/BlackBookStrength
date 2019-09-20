package com.shervinf.blackbookstrength;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CalorieFragment extends Fragment {

    private ArrayList<CaloriePOJO> mArrayList = new ArrayList<>();
    private CalorieAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflating weight in fragment
        View view = inflater.inflate(R.layout.fragment_calorie, container, false);
        fabSetup(view);
        //This method shows the recycler view list
        recyclerViewSetup(view);
        return view;
    }
    private void fabSetup(View v){
        FloatingActionButton fab;
        //Casting floating action button to respective ID
        fab = (FloatingActionButton) v.findViewById(R.id.calorie_floating_action_button);
        //Determines whether the floating action button is null or not and then proceed to set the OnClickListener
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //Call method below when floating action button is clicked and that method will show the alert dialog box and let you input your weight for that day
                showAddItemDialog(getContext());
                }
            });
}


    private void prepareCalorieData(String date, String calorie, String calorieUnit){
        //Creating Plain Old Java Object type WeightInPOJO and adding the passed values to add to the recycler view
        CaloriePOJO calorieList = null;
        calorieList = new CaloriePOJO(date,calorie,calorieUnit);
        mArrayList.add(calorieList);
        mAdapter.notifyDataSetChanged();
    }

    private void showAddItemDialog(Context c) {
        //Displaying Alert Dialog Box
        final EditText taskEditText = new EditText(c);
        //Settings soft keyboard to a numberic keyboard when alert dialog box is opened
        taskEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog dialog = new AlertDialog.Builder(c, R.style.Theme_AppCompat_Dialog_Alert)
                .setTitle("Add weight")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        prepareCalorieData(currentDate(), task,"Cal");
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    private void recyclerViewSetup(View v){
        RecyclerView mRecyclerView1;
        mRecyclerView1 = v.findViewById(R.id.calorieRecyclerView);
        mAdapter = new CalorieAdapter(mArrayList);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }

    public static String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // get current date time with Date()
        Date date = new Date();
        // System.out.println(dateFormat.format(date));
        // don't print it, but save it!
        return dateFormat.format(date);
    }
}
