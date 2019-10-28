package com.shervinf.blackbookstrength;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LiftFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lift,container,false);
        //Code to setup tabs with their corresponding adapters
        ViewPager viewPager = view.findViewById(R.id.pager);
        LiftPagerAdapter myPagerAdapter = new LiftPagerAdapter(getFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tablayout = view.findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewPager);
        return view;
    }
}
