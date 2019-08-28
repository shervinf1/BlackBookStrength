package com.shervinf.blackbookstrength;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyLogsPagerAdapter extends FragmentStatePagerAdapter {
    public MyLogsPagerAdapter(FragmentManager fm){
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
    public int getCount() {
        return 2;
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
}
