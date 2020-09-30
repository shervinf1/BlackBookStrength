package com.shervinf.blackbookstrength;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;
    private FirebaseAuth mAuth;
    private SharedPreferences sp;
    private TextView TextViewEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        sp = getActivity().getSharedPreferences("logged", Context.MODE_PRIVATE);
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        getUserEmail(view);
        recyclerViewSetup(view);
        prepareData();
        return view;
    }




    private void getUserEmail(View view){
        TextViewEmail = view.findViewById(R.id.usernameTextView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String email = user.getEmail();
        assert email != null;
        int index = email.indexOf('@');
        email = email.substring(0,index);
        email = "Welcome " + email+"!";
        TextViewEmail.setText(email);
    }


    private void prepareData() {
        SettingsPOJO settings = null;
        settings = new SettingsPOJO("Edit Profile","Edit your profile");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit 1RM's","Edit your One Reps Max's");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Goals","Edit your goals");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Assistance Exercises","Edit your assistance exercise's");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Sign Out","Log out of this account");
        mArrayList.add(settings);
        mAdapter.notifyDataSetChanged();
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
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
                        Intent editAssistanceExerciseIntent = new Intent(getActivity(), EditAssistanceExerciseActivity.class);
                        startActivity(editAssistanceExerciseIntent);
                        break;
                    case 4:
                        alertSignout();
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.setAdapter(mAdapter);
    }




    private void alertSignout() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getActivity());
        // Setting Dialog Title
        alertDialog2.setTitle("Confirm Sign Out");
        // Setting Dialog Message
        alertDialog2.setMessage("Are you sure you want to Sign out?");
        // Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
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
                        dialog.cancel();
                    }
                });
        // Showing Alert Dialog
        alertDialog2.show();
    }
}
