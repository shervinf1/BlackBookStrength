package com.shervinf.blackbookstrength;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class WeightInFragment extends Fragment {
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference weightInLogReference = db.collection("users").document(userID).collection("weightInLog");
    private WeightInAdapter mWeightInAdapter;
    private TextView progressBarTextView;
    private int weightInGoal;
    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");





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
                customCalenderWeightinDialogBuilder();
                }
            });
    }




    private void progressBarSetup(final View v){
        final ProgressBar mProgressBar = v.findViewById(R.id.calorieProgressBar);
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    UserPOJO newUser = document.toObject(UserPOJO.class);
                    assert newUser != null;
                    Double dMax = newUser.getWeightGoal();
                    weightInGoal = dMax.intValue();
                    mProgressBar.setMax(weightInGoal);
                    setCurrentProgress(weightInGoal, v);
                    Log.d("ProgressBarWeightin","" + weightInGoal);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }








    private void setCurrentProgress(final int weightInGoal, View v) {
        final ProgressBar mProgressBar = v.findViewById(R.id.calorieProgressBar);
        final TextView progressBarTextView = v.findViewById(R.id.progressBarTextView);
        weightInLogReference
            .orderBy("timeStamp", Query.Direction.DESCENDING)
            .limit(1)
            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    progressBarTextView.setText("");
                    mProgressBar.setProgress(0);
                }
                else {
                    WeightInPOJO weightInPOJO = queryDocumentSnapshots.getDocuments().get(0).toObject(WeightInPOJO.class);
                    String weight = weightInPOJO.getWeight();
                    Integer weightValue = Integer.parseInt(weight);
                    progressBarTextView.setText("Current: " + weightValue + "\nGoal: " + weightInGoal);
                    mProgressBar.setProgress(weightValue);
                }


            }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });
    }



    public void customCalenderWeightinDialogBuilder(){
        final android.app.AlertDialog dialogBuilder = new android.app.AlertDialog.Builder(getContext()).create();
        Objects.requireNonNull(dialogBuilder.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_calender_dialog, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.DatePicker);
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
                String date = (datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear() + " 00:00:00");
                if (date.trim().isEmpty()){
                    Toast.makeText(getContext(), "Please insert acceptable value", Toast.LENGTH_SHORT).show();
                }
                else {
                    dialogBuilder.dismiss();
                    customWeightinDialogBuilder(date);
                }
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }








    private void customWeightinDialogBuilder(final String selectedDate){
        final android.app.AlertDialog dialogBuilder = new android.app.AlertDialog.Builder(getContext()).create();
        Objects.requireNonNull(dialogBuilder.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_weightin_dialog, null);
        final EditText weightinEditText = dialogView.findViewById(R.id.edit_weightin_comment);
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
                String weightinValue = String.valueOf(weightinEditText.getText());
                if (weightinValue.trim().isEmpty()){
                    Toast.makeText(getContext(), "Please insert acceptable value", Toast.LENGTH_SHORT).show();
                }
                else {
                    weightInLogReference.add(new WeightInPOJO(weightinValue,"lbs",getTimeStampFromString(selectedDate)));
                    Toast.makeText(getContext(), "Weightin added", Toast.LENGTH_SHORT).show();
                    progressBarSetup(getView());
                    dialogBuilder.dismiss();
                }
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }









    private static Date getTimeStampFromString(String dateToSave) {
        try {
            Date date = format.parse(dateToSave);
            return date ;
        } catch (ParseException e){
            return null ;
        }
    }





    private void recyclerViewSetup(View v){
        Query query = weightInLogReference.orderBy("timeStamp", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<WeightInPOJO> options = new FirestoreRecyclerOptions.Builder<WeightInPOJO>()
                .setQuery(query, WeightInPOJO.class)
                .build();
        mWeightInAdapter = new WeightInAdapter(options);
        RecyclerView mRecyclerView = v.findViewById(R.id.weightInRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView.setAdapter(mWeightInAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mWeightInAdapter.deleteItem(viewHolder.getAdapterPosition());
                progressBarSetup(getView());
            }
        }).attachToRecyclerView(mRecyclerView);
    }


    @Override
    public void onResume() {
        super.onResume();
        progressBarSetup(getView());
    }

    @Override
    public void onStart() {
        super.onStart();
        mWeightInAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mWeightInAdapter.stopListening();
    }


}
