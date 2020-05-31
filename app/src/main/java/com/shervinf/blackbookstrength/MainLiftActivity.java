package com.shervinf.blackbookstrength;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainLiftActivity extends AppCompatActivity {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference mainLiftCollectionReference;
    private MainLiftAdapter mainLiftAdapter;
    private static DecimalFormat df2 = new DecimalFormat("#.##");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lift);

        Intent intent = getIntent();
        String collectionName = intent.getStringExtra("collectionName");
        String mainLiftName = intent.getStringExtra("mainLiftName");
        mainLiftCollectionReference = db.collection("users").document(userID).collection(collectionName);

        toolbarSetup(mainLiftName);
        recyclerViewSetup();
    }





    //Creating toolbar back button to return to previous activity
    public void toolbarSetup(String mlName) {
        Toolbar mToolbar = findViewById(R.id.mainLiftToolbar);
        TextView toolbarTextView = findViewById(R.id.mainLiftToolbarTextView);
        toolbarTextView.setText(mlName);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }








    //Method that find recycler view by the id and displays it.
    public void recyclerViewSetup(){
        Query query = mainLiftCollectionReference.orderBy("priority",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<MainLiftPOJO> options = new FirestoreRecyclerOptions.Builder<MainLiftPOJO>()
                .setQuery(query, MainLiftPOJO.class)
                .build();
        mainLiftAdapter = new MainLiftAdapter(options);
        RecyclerView mRecyclerView = findViewById(R.id.mainLiftRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView.setAdapter(mainLiftAdapter);
        mainLiftAdapter.setOnItemClickListener(new MainLiftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                MainLiftPOJO mainLift = documentSnapshot.toObject(MainLiftPOJO.class);
                boolean isChecked = mainLift.getChecked();
                if (!isChecked){
                    documentSnapshot.getReference().update("checked",true);
                }
                else{
                    documentSnapshot.getReference().update("checked",false);
                }
            }
        });
        Log.d("BlackBookStrength", "The application stopped after DeadLiftActivity.java");
    }





    @Override
    public void onStart() {
        super.onStart();
        mainLiftAdapter.startListening();
    }




    @Override
    public void onStop() {
        super.onStop();
        mainLiftAdapter.stopListening();
    }
}