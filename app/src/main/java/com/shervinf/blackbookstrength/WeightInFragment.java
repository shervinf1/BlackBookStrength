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
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class WeightInFragment extends Fragment {
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference weightInReference = db.collection("users").document(userID).collection("weightInLog");
    private WeightInAdapter weightInAdapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflating weight in fragment
        View view = inflater.inflate(R.layout.fragment_weight_in, container, false);
        //Determines whether the floating action button is null or not and then proceed to set the OnClickListener
        fabSetup(view);
        //This method shows the recycler view list
        recyclerViewSetup(view);
        return view;
    }




    private void fabSetup(View v){
        FloatingActionButton fab;
        //Casting floating action button to respective ID
        fab = v.findViewById(R.id.weightIn_floating_action_button);
        //Determines whether the floating action button is null or not and then proceed to set the OnClickListener
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Call method below when floating action button is clicked and that method will show the alert dialog box and let you input your weight for that day
                    customWeightinDialogBuilder();
                }
            });
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
                if (task.trim().isEmpty()){
                    Toast.makeText(getContext(), "Please insert acceptable value", Toast.LENGTH_SHORT).show();
                }
                else {
                    weightInReference.add(new WeightInPOJO(task,"lbs",currentDate()));
                    Toast.makeText(getContext(), "WeightIn added", Toast.LENGTH_SHORT).show();
                    dialogBuilder.dismiss();
                }
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }







    private void recyclerViewSetup(View v){
        Query query = weightInReference.orderBy("date", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<WeightInPOJO> options = new FirestoreRecyclerOptions.Builder<WeightInPOJO>()
                .setQuery(query, WeightInPOJO.class)
                .build();
        weightInAdapter = new WeightInAdapter(options);
        RecyclerView mRecyclerView = v.findViewById(R.id.weightInRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(weightInAdapter);
    }





    private static String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("LLL-dd-yyyy", Locale.US);
        // get current date time with Date()
        Date date = new Date();
        // System.out.println(dateFormat.format(date));
        // don't print it, but save it!
        return dateFormat.format(date);
    }





    @Override
    public void onStart() {
        super.onStart();
        weightInAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        weightInAdapter.stopListening();
    }


}
