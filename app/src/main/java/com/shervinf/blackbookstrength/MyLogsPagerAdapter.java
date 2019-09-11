package com.shervinf.blackbookstrength;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
