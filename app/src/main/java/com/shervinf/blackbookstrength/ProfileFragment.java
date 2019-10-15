package com.shervinf.blackbookstrength;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private RecyclerView mRecyclerView1;
    private CustomSettingsAdapter mAdapter;
    TextView tvUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        recyclerViewSetup(view);
        prepareData();
        return view;
    }

    private void prepareData() {
        SettingsPOJO settings = null;
        settings = new SettingsPOJO("Edit Deadlift 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Deadlift 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Squat 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Bench 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit OHP 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Calorie Goal","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Weight Goal","Settings Sub Label");
        mArrayList.add(settings);
        mAdapter.notifyDataSetChanged();
    }

    private void recyclerViewSetup(View v){
        mRecyclerView1 = v.findViewById(R.id.settingsRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnExerciseClickListener() {
            @Override
            public void onExerciseViewItemClicked(int position, int id) {

                switch(position) {
                    case 0:
                        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }

}
