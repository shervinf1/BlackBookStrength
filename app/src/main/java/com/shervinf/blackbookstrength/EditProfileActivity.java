package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbarSetup();
        recyclerViewSetup();
        prepareData();
    }

    public void toolbarSetup(){
        Toolbar mToolbar = findViewById(R.id.editProfileToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });
    }
    private void prepareData() {
        SettingsPOJO settings = null;
        settings = new SettingsPOJO("Edit Username","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Change Password","Settings Sub Label");
        mArrayList.add(settings);

        mAdapter.notifyDataSetChanged();
    }


    private void recyclerViewSetup() {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.editProfileRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Toast.makeText(EditProfileActivity.this, "0", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(EditProfileActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(EditProfileActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }
}
