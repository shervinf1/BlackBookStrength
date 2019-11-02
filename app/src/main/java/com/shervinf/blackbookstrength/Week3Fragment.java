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

public class Week3Fragment extends Fragment {
    private ArrayList<ExercisePOJO> mArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView3;
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
        mRecyclerView3 = v.findViewById(R.id.recyclerViewWeek);
        mAdapter = new ExerciseAdapter(mArrayList, new OnExerciseClickListener() {
            @Override
            public void onExerciseViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Intent deadliftIntent = new Intent(getActivity(), DeadliftActivityWeek3.class);
                        startActivity(deadliftIntent);
                        break;
                    case 1:
                        Intent BenchIntent = new Intent(getActivity(), BenchActivityWeek3.class);
                        startActivity(BenchIntent);
                        break;
                    case 2:
                        Intent SquatIntent = new Intent(getActivity(), SquatActivityWeek3.class);
                        startActivity(SquatIntent);
                        break;
                    case 3:
                        Intent OHPIntent = new Intent(getActivity(), OHPActivityWeek3.class);
                        startActivity(OHPIntent);
                        break;
                }
            }
        });
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView3.setItemAnimator( new DefaultItemAnimator());
//        mRecyclerView3.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView3.setAdapter(mAdapter);
    }
}