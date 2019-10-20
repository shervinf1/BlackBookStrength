package com.shervinf.blackbookstrength;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class ProfileFragment extends Fragment {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        recyclerViewSetup(view);
        prepareData();
        mAuth = getInstance();
        return view;
    }


private void prepareData() {
    SettingsPOJO settings = null;
    settings = new SettingsPOJO("Edit Profile","Settings Sub Label");
    mArrayList.add(settings);
    settings = new SettingsPOJO("Edit 1RM's","Settings Sub Label");
    mArrayList.add(settings);
    settings = new SettingsPOJO("Edit Goals","Settings Sub Label");
    mArrayList.add(settings);
    settings = new SettingsPOJO("Sign Out","Log out of this account");
    mArrayList.add(settings);
    mAdapter.notifyDataSetChanged();
}

    private void recyclerViewSetup(View v) {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = v.findViewById(R.id.settingsRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Intent editProfileIntent = new Intent(getActivity(), EditProfileActivity.class);
                        startActivity(editProfileIntent);
                        break;
                    case 1:
                        Intent edit1rmIntent = new Intent(getActivity(), Edit1rmActivity.class);
                        startActivity(edit1rmIntent);
                        break;
                    case 2:
                        Intent editGoalsIntent = new Intent(getActivity(), EditGoalsActivity.class);
                        startActivity(editGoalsIntent);
                        break;
                    case 3:
//                        signOutExistingUser();
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }
    private void signOutExistingUser(){
        FirebaseAuth.getInstance().signOut();
        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
        startActivity(loginIntent);

    }
}
