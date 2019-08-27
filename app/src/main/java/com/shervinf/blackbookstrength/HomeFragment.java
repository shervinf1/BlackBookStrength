package com.shervinf.blackbookstrength;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        //Code to setup tabs with their corresponding adapters
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        MyPageAdapter myPagerAdapter = new MyPageAdapter(getFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewPager);

        return view;
    }
}
