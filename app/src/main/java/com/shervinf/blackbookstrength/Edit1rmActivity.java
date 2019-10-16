package com.shervinf.blackbookstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit1rmActivity extends AppCompatActivity {

    private ArrayList<SettingsPOJO> mArrayList = new ArrayList<>();
    private CustomSettingsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_1rm);
        toolbarSetup();
        recyclerViewSetup();
        prepareData();


    }

    public void toolbarSetup(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.edit1rmToolbar);
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
        settings = new SettingsPOJO("Edit Deadlift 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Squat 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit Bench 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        settings = new SettingsPOJO("Edit OHP 1RPM","Settings Sub Label");
        mArrayList.add(settings);
        mAdapter.notifyDataSetChanged();
    }


    private void recyclerViewSetup() {
        RecyclerView mRecyclerView1;
        mRecyclerView1 = findViewById(R.id.edit1rmRecyclerView);
        mAdapter = new CustomSettingsAdapter(mArrayList, new OnSettingsClickListener() {
            @Override
            public void onSettingsViewItemClicked(int position, int id) {
                switch(position) {
                    case 0:
                        Toast.makeText(Edit1rmActivity.this, "0", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(Edit1rmActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(Edit1rmActivity.this, "2", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(Edit1rmActivity.this, "3", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView1.setItemAnimator( new DefaultItemAnimator());
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(Edit1rmActivity.this, LinearLayoutManager.VERTICAL));
        mRecyclerView1.setAdapter(mAdapter);
    }

}
