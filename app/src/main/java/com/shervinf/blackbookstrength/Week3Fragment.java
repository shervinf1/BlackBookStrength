package com.shervinf.blackbookstrength;
import android.content.Intent;
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

import java.util.ArrayList;

public class Week3Fragment extends Fragment {
    private ArrayList<Exercise> mArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView3;
    private CustomExerciseAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_week3, container, false);

//      mRecyclerView1 = mRecyclerView1.findViewById(R.id.recyclerView);
        mRecyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerView3);
//        mAdapter = new CustomExerciseAdapter(mArrayList, new OnExerciseClickListener() {
//            @Override
//            public void onExerciseViewItemClicked(int position, int id) {
//                Toast.makeText(getActivity().getBaseContext(),""+position,Toast.LENGTH_SHORT).show();
//            }
//        });
        mAdapter = new CustomExerciseAdapter(mArrayList, new OnExerciseClickListener() {
            @Override
            public void onExerciseViewItemClicked(int position, int id) {

//                Toast.makeText(getActivity().getBaseContext(),""+position,Toast.LENGTH_SHORT).show();
                switch(position) {
                    case 0:
                        Intent deadliftIntent = new Intent(getActivity(), DeadliftActivity.class);
                        startActivity(deadliftIntent);
                        break;
                    case 1:
                        Intent BenchIntent = new Intent(getActivity(), BenchActivity.class);
                        startActivity(BenchIntent);
                        break;
                    case 2:
                        Intent SquatIntent = new Intent(getActivity(), SquatActivity.class);
                        startActivity(SquatIntent);
                        break;
                    case 3:
                        Intent OHPIntent = new Intent(getActivity(), OHPActivity.class);
                        startActivity(OHPIntent);
                        break;
                }
            }
        });
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView3.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView3.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView3.setAdapter(mAdapter);

        mainLift();

        return view;
    }


    private void mainLift() {

        Exercise exerciseList = null;

        exerciseList = new Exercise("DEADLIFT");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("BENCH");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("SQUAT");
        mArrayList.add(exerciseList);
        exerciseList = new Exercise("OHP");
        mArrayList.add(exerciseList);

        mAdapter.notifyDataSetChanged();
    }
}