package com.shervinf.blackbookstrength;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

public class MyPageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    MyPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Week1Fragment();
            case 1:
                return new Week2Fragment();
            case 2:
                return new Week3Fragment();
            case 3:
                return new Week4Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "WEEK 1";
            case 1:
                return "WEEK 2";
            case 2:
                return "WEEK 3";
            case 3:
                return "DELOAD";
            default:
                return null;
        }
    }
}
