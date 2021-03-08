package com.admereselvyn.mic;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;

public class MySubmissionFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_submission, container, false);

        TabLayout tabLayoutMySubmission =view. findViewById(R.id.tabLayoutMySubmission);
        ViewPager vPagerMySubmission = view.findViewById(R.id.vPagerMySubmission);

        MySubmissionPageAdapter pageAdapter = new MySubmissionPageAdapter (getChildFragmentManager(), tabLayoutMySubmission.getTabCount());
        vPagerMySubmission.setAdapter(pageAdapter);




        tabLayoutMySubmission.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                vPagerMySubmission.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0)
                    pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vPagerMySubmission.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMySubmission));

        return view;
    }
}