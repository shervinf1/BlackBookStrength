package com.shervinf.blackbookstrength;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


public class CalorieFragment extends Fragment {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference calorieLogCollectionReference = db.collection("users").document(userID).collection("calorieLog");
    private CalorieAdapter calorieAdapter;
    private ProgressBar mProgressBar;
    private int calorieGoal;
    private static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
                customCalenderCalorieDialogBuilder();
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
                    UserPOJO newUser = document.toObject(UserPOJO.class);
                    Integer iMax = newUser.getCalorieGoal();
                    mProgressBar.setMax(iMax);
                    setCurrentProgress(iMax, v);
                    Log.d("ProgressBarCalorie","" + iMax);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }



    private void setCurrentProgress(final int calorieGoal, View v) {
        final ProgressBar mProgressBar = v.findViewById(R.id.calorieProgressBar);
        final TextView progressBarTextView = v.findViewById(R.id.progressBarTextView);
        calorieLogCollectionReference
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
                    CaloriePOJO caloriePOJO = queryDocumentSnapshots.getDocuments().get(0).toObject(CaloriePOJO.class);
                    String weight = caloriePOJO.getCalorie();
                    Integer calorieValue = Integer.parseInt(weight);
                    progressBarTextView.setText("Current: " + calorieValue + "\nGoal: " + calorieGoal);
                    mProgressBar.setProgress(calorieValue);
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }








    private void customCalenderCalorieDialogBuilder(){
        final android.app.AlertDialog dialogBuilder = new android.app.AlertDialog.Builder(getContext()).create();
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
                    customCalorieDialogBuilder(date);
                }

            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }





    private void customCalorieDialogBuilder(final String selectedDate){
        final android.app.AlertDialog dialogBuilder = new android.app.AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_calorie_dialog, null);
        final EditText calorieEditText = dialogView.findViewById(R.id.edit_calorie_comment);
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
                String weightinValue = String.valueOf(calorieEditText.getText());
                if (weightinValue.trim().isEmpty()){
                    Toast.makeText(getContext(), "Please insert acceptable value", Toast.LENGTH_SHORT).show();
                }
                else {
                    calorieLogCollectionReference.add(new CaloriePOJO(weightinValue,"Cal",getTimeStampFromString(selectedDate)));
                    Toast.makeText(getContext(), "Calories added", Toast.LENGTH_SHORT).show();
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
        Query query = calorieLogCollectionReference.orderBy("timeStamp", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<CaloriePOJO> options = new FirestoreRecyclerOptions.Builder<CaloriePOJO>()
                .setQuery(query, CaloriePOJO.class)
                .build();
        calorieAdapter = new CalorieAdapter(options);
        RecyclerView mRecyclerView = v.findViewById(R.id.calorieRecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView.setAdapter(calorieAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                calorieAdapter.deleteItem(viewHolder.getAdapterPosition());
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
        calorieAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        calorieAdapter.stopListening();
    }


}
