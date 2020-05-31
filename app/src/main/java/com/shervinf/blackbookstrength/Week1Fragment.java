package com.shervinf.blackbookstrength;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Week1Fragment extends Fragment {


    private ArrayList<ExercisePOJO> mArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView1;
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
        exercisePOJOList = new ExercisePOJO("DEADLIFT");
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO("BENCH");
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO("SQUAT");
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO("OHP");
        mArrayList.add(exercisePOJOList);
        mAdapter.notifyDataSetChanged();
    }





    private void recyclerViewSetup(View v){
        mRecyclerView1 = v.findViewById(R.id.recyclerViewWeek);
        mAdapter = new ExerciseAdapter(mArrayList, new OnExerciseClickListener() {
            @Override
            public void onExerciseViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Intent deadliftIntent = new Intent(getActivity(), MainLiftActivity.class);
                        deadliftIntent.putExtra("collectionName", "deadliftWeek1");
                        deadliftIntent.putExtra("mainLiftName","Deadlift");
                        deadliftIntent.putExtra("percentageDecimal",MainLiftPOJO.PERCENT_65);
                        deadliftIntent.putExtra("percentageInteger", 65);
                        deadliftIntent.putExtra("set1","% x 5 REPS");
                        deadliftIntent.putExtra("set2","% x 5 REPS");
                        deadliftIntent.putExtra("set3","% x 5 REPS");
                        startActivity(deadliftIntent);
                        break;
                    case 1:
                        Intent BenchIntent = new Intent(getActivity(), MainLiftActivity.class);
                        BenchIntent.putExtra("collectionName", "benchWeek1");
                        BenchIntent.putExtra("mainLiftName","Bench");
                        BenchIntent.putExtra("percentageDecimal",MainLiftPOJO.PERCENT_65);
                        BenchIntent.putExtra("percentageInteger", 65);
                        BenchIntent.putExtra("set1","% x 5 REPS");
                        BenchIntent.putExtra("set2","% x 5 REPS");
                        BenchIntent.putExtra("set3","% x 5 REPS");
                        startActivity(BenchIntent);
                        break;
                    case 2:
                        Intent SquatIntent = new Intent(getActivity(), MainLiftActivity.class);
                        SquatIntent.putExtra("collectionName", "squatWeek1");
                        SquatIntent.putExtra("mainLiftName","Squat");
                        SquatIntent.putExtra("percentageDecimal",MainLiftPOJO.PERCENT_65);
                        SquatIntent.putExtra("percentageInteger", 65);
                        SquatIntent.putExtra("set1","% x 5 REPS");
                        SquatIntent.putExtra("set2","% x 5 REPS");
                        SquatIntent.putExtra("set3","% x 5 REPS");
                        startActivity(SquatIntent);
                        break;
                    case 3:
                        Intent OHPIntent = new Intent(getActivity(), MainLiftActivity.class);
                        OHPIntent.putExtra("collectionName", "ohpWeek1");
                        OHPIntent.putExtra("mainLiftName","OHP");
                        OHPIntent.putExtra("percentageDecimal",MainLiftPOJO.PERCENT_65);
                        OHPIntent.putExtra("percentageInteger", 65);
                        OHPIntent.putExtra("set1","% x 5 REPS");
                        OHPIntent.putExtra("set2","% x 5 REPS");
                        OHPIntent.putExtra("set3","% x 5 REPS");
                        startActivity(OHPIntent);
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
//        mRecyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }
}