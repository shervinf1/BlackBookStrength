package com.shervinf.blackbookstrength;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class WeightInFragment extends Fragment {

    private ArrayList<WeightInPOJO> mArrayList = new ArrayList<>();
    private WeightInAdapter mAdapter;
    private FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        FloatingActionButton fab;


        //Inflating weight in fragment
        View view = inflater.inflate(R.layout.fragment_weight_in, container, false);
        //Casting floating action button to respective ID
        fab = view.findViewById(R.id.weightIn_floating_action_button);
        //Determines whether the floating action button is null or not and then proceed to set the OnClickListener
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Call method below when floating action button is clicked and that method will show the alert dialog box and let you input your weight for that day
                    customWeightinDialogBuilder();
                }
            });
        //This method shows the recycler view list
        recyclerViewSetup(view);
        db = FirebaseFirestore.getInstance();
        return view;
    }


    public void prepareWeightInData(String date, String weight, String weightUnit){
        //Creating Plain Old Java Object type WeightInPOJO and adding the passed values to add to the recucler view
        WeightInPOJO weightInList = null;
        weightInList = new WeightInPOJO(date,weight,weightUnit);
        mArrayList.add(weightInList);
        mAdapter.notifyDataSetChanged();
    }

    public void customWeightinDialogBuilder(){
        final android.app.AlertDialog dialogBuilder = new android.app.AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_weightin_dialog, null);
        final EditText taskEditText = dialogView.findViewById(R.id.edit_weightin_comment);
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
                prepareWeightInData(currentDate(), task,"lbs");
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
        mRecyclerView1 = v.findViewById(R.id.weightInRecyclerView);
        mAdapter = new WeightInAdapter(mArrayList);
        mRecyclerView1.setLayoutManager(mLayoutManager);
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }

    private static String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("LLL-dd-yyyy", Locale.US);
        // get current date time with Date()
        Date date = new Date();
        // System.out.println(dateFormat.format(date));
        // don't print it, but save it!
        return dateFormat.format(date);
    }
}
