package com.shervinf.blackbookstrength;

import androidx.annotation.NonNull;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class Edit1rmActivity extends AppCompatActivity {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_1rm);
        toolbarSetup();
        recyclerViewSetup();
        prepareData();
        db = FirebaseFirestore.getInstance();
    }

    //Method that displays back button in toolbar and ends this activity when button is clicked.
    public void toolbarSetup(){
        Toolbar mToolbar =findViewById(R.id.edit1rmToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }



    //Method that adds data into object array list type SettingsPOJO and display it in recycler view.
    private void prepareData() {
        SettingsPOJO settings;
        settings = new SettingsPOJO("Edit Deadlift 1RPM","Change one rep max for Deadlift");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Squat 1RPM","Change one rep max for Squat");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Bench 1RPM","Change one rep max for Bench");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit OHP 1RPM","Change one rep max for OHP");
        mArrayList.add(settings);
        mAdapter.notifyDataSetChanged();
    }



    //Method that find recycler view by the id and displays it.
    private void recyclerViewSetup() {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.edit1rmRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        customDialogBuilder(position);
                        break;
                    case 1:
                        customDialogBuilder(position);
                        break;
                    case 2:
                        customDialogBuilder(position);
                        break;
                    case 3:
                        customDialogBuilder(position);
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(Edit1rmActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }




    //Method to create Alert Dialog for changing weightGoal attribute
    //In Firebase cloud firestore database
    public void customDialogBuilder(final int position){
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_1rm_decimal_input_dialog, null);
        final EditText editText = dialogView.findViewById(R.id.edt_comment);
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
                String attrbuteUpdate =null;
                switch (position){
                    case 0: attrbuteUpdate = "deadliftMax";break;
                    case 1: attrbuteUpdate = "squatMax";break;
                    case 2: attrbuteUpdate = "benchMax";break;
                    case 3: attrbuteUpdate = "ohpMax";break;
                }
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DocumentReference newUserDocumentRef = db.collection("users").document(userID);
                newUserDocumentRef
                        .update(attrbuteUpdate,Double.parseDouble(editText.getText().toString()))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("BlackBookStrength","successfully updated maximum attribute");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("BlackBookStrength","Failed to update maximum attribute");
                                e.printStackTrace();
                            }
                        });
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }



}

