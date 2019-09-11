package com.shervinf.blackbookstrength;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logs,container,false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.logsPager);
        MyLogsPagerAdapter MyLogsPagerAdapter = new MyLogsPagerAdapter(getFragmentManager());
        viewPager.setAdapter(MyLogsPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.logsTablayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
