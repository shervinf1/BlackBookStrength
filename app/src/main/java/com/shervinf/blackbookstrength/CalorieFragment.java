package com.shervinf.blackbookstrength;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class CalorieFragment extends Fragment {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference calorieLogCollectionReference = db.collection("users").document(userID).collection("calorieLog");
    private CalorieAdapter calorieAdapter;
    private ProgressBar mProgressBar;





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
                customCalorieDialogBuilder();
                }
            });
}





    private void customCalorieDialogBuilder(){
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
                if (task.trim().isEmpty()){
                    Toast.makeText(getContext(), "Please insert acceptable value", Toast.LENGTH_SHORT).show();
                }
                else {
                    calorieLogCollectionReference.add(new CaloriePOJO(task,"Cal",currentDate()));
                    Toast.makeText(getContext(), "Calories added", Toast.LENGTH_SHORT).show();
                    dialogBuilder.dismiss();
                }

            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }






    private void recyclerViewSetup(View v){
        Query query = calorieLogCollectionReference.orderBy("date", Query.Direction.ASCENDING);
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
            }
        }).attachToRecyclerView(mRecyclerView);
    }



    public static String currentDate() {
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
        calorieAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        calorieAdapter.stopListening();
    }


}
