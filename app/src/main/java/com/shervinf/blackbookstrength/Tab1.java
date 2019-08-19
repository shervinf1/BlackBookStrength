package com.shervinf.blackbookstrength;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shervinf.blackbookstrength.R;

import java.util.ArrayList;

public class Tab1 extends Fragment {
    private ArrayList<Exercise> mArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView1;
    private CustomExerciseAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

//      mRecyclerView1 = mRecyclerView1.findViewById(R.id.recyclerView);
        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerView);
        mAdapter = new CustomExerciseAdapter(mArrayList);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);

        prepareData();

        return view;
    }


    private void prepareData() {

        Exercise exerciseList = null;

        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("Deadlift","3","5");

        mArrayList.add(exerciseList);

        mAdapter.notifyDataSetChanged();
    }
}