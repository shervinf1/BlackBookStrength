package com.shervinf.blackbookstrength;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Week3Fragment extends Fragment {
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
        exercisePOJOList = new ExercisePOJO(R.drawable.deadlift);
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO(R.drawable.bench_press);
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO(R.drawable.squat);
        mArrayList.add(exercisePOJOList);
        exercisePOJOList = new ExercisePOJO(R.drawable.ohp);
        mArrayList.add(exercisePOJOList);
        mAdapter.notifyDataSetChanged();
    }



    private void recyclerViewSetup(View v){
        RecyclerView mRecyclerView3 = v.findViewById(R.id.recyclerViewWeek);
        mAdapter = new ExerciseAdapter(mArrayList, new OnExerciseClickListener() {
            @Override
            public void onExerciseViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Intent deadliftIntent = new Intent(getActivity(), MainLiftActivity.class);
                        deadliftIntent.putExtra("collectionName", "deadliftWeek3");
                        deadliftIntent.putExtra("mainLiftName","Deadlift");
                        startActivity(deadliftIntent);
                        break;
                    case 1:
                        Intent BenchIntent = new Intent(getActivity(), MainLiftActivity.class);
                        BenchIntent.putExtra("collectionName", "benchWeek3");
                        BenchIntent.putExtra("mainLiftName","Bench");
                        startActivity(BenchIntent);
                        break;
                    case 2:
                        Intent SquatIntent = new Intent(getActivity(), MainLiftActivity.class);
                        SquatIntent.putExtra("collectionName", "squatWeek3");
                        SquatIntent.putExtra("mainLiftName","Squat");
                        startActivity(SquatIntent);
                        break;
                    case 3:
                        Intent OHPIntent = new Intent(getActivity(), MainLiftActivity.class);
                        OHPIntent.putExtra("collectionName", "ohpWeek3");
                        OHPIntent.putExtra("mainLiftName","OHP");
                        startActivity(OHPIntent);
                        break;
                }
            }
        });
        mRecyclerView3.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView3.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView3.setAdapter(mAdapter);
    }
}