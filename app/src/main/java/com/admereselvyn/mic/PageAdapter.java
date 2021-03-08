package com.admereselvyn.mic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private final FragmentManager fragmentManager;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
        fragmentManager=fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0: return new MySubmissionFragment();

            case 1: return new ContestFragment();

            case 2: return new ProfileFragment();

            default: return new Fragment();

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
