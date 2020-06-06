package com.shervinf.blackbookstrength;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Week4Fragment extends Fragment {
    private ArrayList<ExercisePOJO> mArrayList = new ArrayList<>();
    private ExerciseAdapter mAdapter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_week, container, false);
        recyclerViewSetup(view);
        prepareData();
        return view;
    }





    private void prepareData() {
        ExercisePOJO exercisePOJOList = null;
        exercisePOJOList = new ExercisePOJO(R.drawable.deadlift_photo, "Deadlift");
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO(R.drawable.bench_press_photo, "Bench");
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO(R.drawable.squat_photo, "Squat");
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO(R.drawable.ohp_photo, "OHP");
        mArrayList.add(exercisePOJOList);
        mAdapter.notifyDataSetChanged();
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
        mRecyclerView4.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView4.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView4.setAdapter(mAdapter);
    }
}