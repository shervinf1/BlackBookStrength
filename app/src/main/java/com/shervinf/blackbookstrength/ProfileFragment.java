package com.shervinf.blackbookstrength;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;
    private FirebaseAuth mAuth;
    SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        sp = this.getActivity().getSharedPreferences("logged", Context.MODE_PRIVATE);
    }

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
                        alertSignout();
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }
//    private void signOutExistingUser() {
//        mAuth.getInstance().signOut();
//        Intent loginIntent = new Intent(getContext(), LoginActivity.class);
//        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(loginIntent);
//
//    }

    private void alertSignout() {
        AlertDialog.Builder alertDialog2 = new
                AlertDialog.Builder(
                getActivity());

        // Setting Dialog Title
        alertDialog2.setTitle("Confirm SignOut");

        // Setting Dialog Message
        alertDialog2.setMessage("Are you sure you want to Signout?");

        // Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        mAuth.getInstance().signOut();
                        sp.edit().putBoolean("logged",false).apply();
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                });

        // Setting Negative "NO" Btn
        alertDialog2.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getContext(),
                                "You clicked on NO", Toast.LENGTH_SHORT)
                                .show();
                        dialog.cancel();
                    }
                });

        // Showing Alert Dialog
        alertDialog2.show();


    }
}
