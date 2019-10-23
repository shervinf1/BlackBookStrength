package com.shervinf.blackbookstrength;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogsPagerAdapter extends FragmentStatePagerAdapter {
//    private final List<Fragment> mFragmentList = new ArrayList<>(Arrays.asList(new WeightInFragment(),new CalorieFragment()));
//    private final List<String> mFragmentTitleList = new ArrayList<>(Arrays.asList("Weight In","Calorie"));




    public LogsPagerAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new WeightInFragment();
            case 1: return new CalorieFragment();
        }
        return null;
    }




    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Weight In";
            case 1:
                return "Calorie";
        default:
            return null;
        }
    }




    @Override
    public int getCount() {
        return 2;
    }
}
