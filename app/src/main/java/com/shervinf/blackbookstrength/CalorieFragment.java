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
import android.widget.Button;
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
        fab = v.findViewById(R.id.calorie_floating_action_button);
        //Determines whether the floating action button is null or not and then proceed to set the OnClickListener
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //Call method below when floating action button is clicked and that method will show the alert dialog box and let you input your weight for that day
                customCalorieDialogBuilder();
                }
            });
}




    private void prepareCalorieData(String date, String calorie, String calorieUnit){
        //Creating Plain Old Java Object type WeightInPOJO and adding the passed values to add to the recycler view
        CaloriePOJO calorieList;
        calorieList = new CaloriePOJO(date,calorie,calorieUnit);
        mArrayList.add(calorieList);
        mAdapter.notifyDataSetChanged();
    }




    public void customCalorieDialogBuilder(){
        final android.app.AlertDialog dialogBuilder = new android.app.AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_calorie_dialog, null);
        final EditText taskEditText = dialogView.findViewById(R.id.edit_calorie_comment);
        Button buttonSubmit = dialogView.findViewById(R.id.buttonSubmit);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = String.valueOf(taskEditText.getText());
                prepareCalorieData(currentDate(), task,"Cal");
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }




    private void recyclerViewSetup(View v){
        RecyclerView mRecyclerView1;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView1 = v.findViewById(R.id.calorieRecyclerView);
        mAdapter = new CalorieAdapter(mArrayList);
        mRecyclerView1.setLayoutManager(mLayoutManager);
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
