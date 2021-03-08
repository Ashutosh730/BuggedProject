package com.admereselvyn.mic;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MySubmissionPageAdapter extends FragmentPagerAdapter {

    private final int tabCount;
    private final FragmentManager fragmentManager;
    public MySubmissionPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
        fragmentManager=fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0: return new MySubmissionVideoFragment();

            //case 1: return new MySubmissionAudioFragment();  This Section Will be for Audio

            default: return new Fragment();

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}