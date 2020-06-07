package com.shervinf.blackbookstrength;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Week4Fragment extends Fragment {
    private ArrayList<ExercisePOJO> mArrayList = new ArrayList<>();
    private ExerciseAdapter mAdapter;
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_week, container, false);
        mArrayList.add(0,new ExercisePOJO());
        mArrayList.add(1,new ExercisePOJO());
        mArrayList.add(2,new ExercisePOJO());
        mArrayList.add(3,new ExercisePOJO());

        getCheckedFromFirestore(view, new Week1Fragment.MyCallback() {
            @Override
            public void onCallback(int i, Boolean exercisePOJOArrayListBoolean) {
                prepareData(i,exercisePOJOArrayListBoolean);
                recyclerViewSetup(view);

            }
        });

        return view;
    }







    private void getCheckedFromFirestore(View view,final Week1Fragment.MyCallback myCallback) {
        final ArrayList<String> collectionNameList = new ArrayList<>();
        collectionNameList.add("deadliftWeek4");
        collectionNameList.add("benchWeek4");
        collectionNameList.add("squatWeek4");
        collectionNameList.add("ohpWeek4");
        for (int i = 0; i < collectionNameList.size();i++){
            final int finalI = i;
            db.collection("users").document(userID).collection(collectionNameList.get(i))
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<MainLiftPOJO> documentList = new ArrayList<>();
                        ArrayList<Boolean> booleanList = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            documentList.add(documentSnapshot.toObject(MainLiftPOJO.class));
                        }
                        for (int j = 0; j < documentList.size(); j++) {
                            booleanList.add(documentList.get(j).getChecked());
                        }
                        myCallback.onCallback(finalI, booleanList.contains(false));
                    }
                    else {
                        Log.d("Week1F", "Error getting documents: ", task.getException());
                    }
                }
            });
        }
    }


    public interface MyCallback {
        void onCallback(int i,Boolean exercisePOJOArrayListBoolean);
    }




    public void prepareData(int pos, Boolean boolValue){
        ExercisePOJO exercisePOJO = null;
        if (boolValue){
            switch (pos) {
                case 0:
                    exercisePOJO = new ExercisePOJO(R.drawable.deadlift_photo_no_color, "Deadlift");
                    mArrayList.set(0, exercisePOJO);
                    break;
                case 1:
                    exercisePOJO = new ExercisePOJO(R.drawable.bench_press_photo_no_color, "Bench");
                    mArrayList.set(1,exercisePOJO);
                    break;
                case 2:
                    exercisePOJO = new ExercisePOJO(R.drawable.squat_photo_no_color, "Squat");
                    mArrayList.set(2,exercisePOJO);
                    break;
                case 3:
                    exercisePOJO = new ExercisePOJO(R.drawable.ohp_photo_no_color, "OHP");
                    mArrayList.set(3,exercisePOJO);
                    break;
            }
        }
        else {
            switch (pos) {
                case 0:
                    exercisePOJO = new ExercisePOJO(R.drawable.deadlift_photo, "Deadlift");
                    mArrayList.set(0,exercisePOJO);
                    break;
                case 1:
                    exercisePOJO = new ExercisePOJO(R.drawable.bench_press_photo, "Bench");
                    mArrayList.set(1,exercisePOJO);
                    break;
                case 2:
                    exercisePOJO = new ExercisePOJO(R.drawable.squat_photo, "Squat");
                    mArrayList.set(2,exercisePOJO);
                    break;
                case 3:
                    exercisePOJO = new ExercisePOJO(R.drawable.ohp_photo, "OHP");
                    mArrayList.set(3,exercisePOJO);
                    break;
            }
        }
    }






    private void recyclerViewSetup(View v){
        RecyclerView mRecyclerView4 = v.findViewById(R.id.recyclerViewWeek);
        mAdapter = new ExerciseAdapter(mArrayList, new OnExerciseClickListener() {
            @Override
            public void onExerciseViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Intent deadliftIntent = new Intent(getActivity(), MainLiftActivity.class);
                        deadliftIntent.putExtra("collectionName", "deadliftWeek4");
                        deadliftIntent.putExtra("mainLiftName","Deadlift");
                        startActivity(deadliftIntent);
                        break;
                    case 1:
                        Intent BenchIntent = new Intent(getActivity(), MainLiftActivity.class);
                        BenchIntent.putExtra("collectionName", "benchWeek4");
                        BenchIntent.putExtra("mainLiftName","Bench");
                        startActivity(BenchIntent);
                        break;
                    case 2:
                        Intent SquatIntent = new Intent(getActivity(), MainLiftActivity.class);
                        SquatIntent.putExtra("collectionName", "squatWeek4");
                        SquatIntent.putExtra("mainLiftName","Squat");
                        startActivity(SquatIntent);
                        break;
                    case 3:
                        Intent OHPIntent = new Intent(getActivity(), MainLiftActivity.class);
                        OHPIntent.putExtra("collectionName", "ohpWeek4");
                        OHPIntent.putExtra("mainLiftName","OHP");
                        startActivity(OHPIntent);
                        break;
                }
            }
        });
        mAdapter.notifyDataSetChanged();
        mRecyclerView4.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView4.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView4.setAdapter(mAdapter);
    }
}