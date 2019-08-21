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
import android.widget.Toast;

import com.shervinf.blackbookstrength.R;

import java.util.ArrayList;

public class Tab2 extends Fragment {
    private ArrayList<Exercise> mArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView2;
    private CustomExerciseAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

//      mRecyclerView1 = mRecyclerView1.findViewById(R.id.recyclerView);
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        mAdapter = new CustomExerciseAdapter(mArrayList, new OnExerciseClickListener() {
            @Override
            public void onExerciseViewItemClicked(int position, int id) {
                Toast.makeText(getActivity().getBaseContext(),""+position,Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView2.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView2.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView2.setAdapter(mAdapter);

        prepareData();

        return view;
    }


    private void prepareData() {

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