package com.admereselvyn.mic;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class UserMicPageAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private final FragmentManager fragmentManager;
    public UserMicPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
        fragmentManager=fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0: return new OnGoingContestFragment();

            case 1: return new UpComingContestFragment();

            default: return new Fragment();

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
