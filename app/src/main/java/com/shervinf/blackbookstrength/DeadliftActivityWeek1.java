package com.shervinf.blackbookstrength;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.util.ArrayList;

public class DeadliftActivityWeek1 extends AppCompatActivity {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private CollectionReference mainLiftCollectionReference = db.collection("users").document(userID).collection("deadliftWeek1");
    private MainLiftAdapter mainLiftAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlift);

        toolbarSetup();
        recyclerViewSetup();
        MainLiftAdapter.prepareData(mainLiftCollectionReference,mainLiftAdapter,
                new MainLiftPOJO((MainLiftAdapter.retrieveDeadliftMax() * MainLiftPOJO.PERCENT_40), "lbs", 40, "% x 5 REPS"),
                new MainLiftPOJO((MainLiftAdapter.retrieveDeadliftMax() * MainLiftPOJO.PERCENT_50), "lbs", 50, "% x 5 REPS"),
                new MainLiftPOJO((MainLiftAdapter.retrieveDeadliftMax() * MainLiftPOJO.PERCENT_60), "lbs", 60, "% x 5 REPS"),
                new MainLiftPOJO((MainLiftAdapter.retrieveDeadliftMax() * MainLiftPOJO.PERCENT_65), "lbs", 65, "% x 5 REPS"),
                new MainLiftPOJO((MainLiftAdapter.retrieveDeadliftMax()* MainLiftPOJO.PERCENT_75), "lbs", 75, "% x 5 REPS"),
                new MainLiftPOJO((MainLiftAdapter.retrieveDeadliftMax() * MainLiftPOJO.PERCENT_85), "lbs", 85, "% x 5 REPS"));
    }





    //Creating toolbar back button to return to previous activity
    public void toolbarSetup() {
        Toolbar mToolbar = findViewById(R.id.deadliftToolbar);
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
        Query query = mainLiftCollectionReference.orderBy("percentage",Query.Direction.ASCENDING).limit(6);
        FirestoreRecyclerOptions<MainLiftPOJO> options = new FirestoreRecyclerOptions.Builder<MainLiftPOJO>()
                .setQuery(query, MainLiftPOJO.class)
                .build();
        mainLiftAdapter = new MainLiftAdapter(options);
        RecyclerView mRecyclerView = findViewById(R.id.deadliftRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
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
        mainLiftAdapter.startListening();;
    }




    @Override
    public void onStop() {
        super.onStop();
        mainLiftAdapter.stopListening();
    }
}
