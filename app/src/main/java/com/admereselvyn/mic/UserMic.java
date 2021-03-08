package com.admereselvyn.mic;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.admereselvyn.mic.api.models.user_model.UserData;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class UserMic extends Fragment {

    // Declaration of variables...
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UserData userLoginData;
    private TextView userMic_userName;
    private ImageView userMic_userImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_mic, container, false);

        // Initialization of variables...
        tabLayout =view. findViewById(R.id.mic_tab_layout);
        viewPager = view.findViewById(R.id.mic_vPager);
        userMic_userImage = view.findViewById(R.id.userMic_userImage);
        userMic_userName = view.findViewById(R.id.userMic_username);

        //Showing userdata using shared preferences
        SharedPreferences sharedpreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userdata", MODE_PRIVATE);
        Gson gson = new Gson();
        String data =sharedpreferences.getString("userdata", null);
        if(data != null) {
            userLoginData = gson.fromJson(data, UserData.class);
            userMic_userName.setText("Hii, "+userLoginData.getName());  //Set name
            if(userLoginData.getLinkToProfileImg()!=null)
            {
                Picasso.with(getContext()).load(userLoginData.getLinkToProfileImg()).placeholder(R.drawable.ic_account_circle)
                        .resize(90,90).into(userMic_userImage);  //set profile photo
            }
        }

        // Implementing tabLayout and viewPager and its swipe gesture and onClick action...
        UserMicPageAdapter userMicPageAdapter = new UserMicPageAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(userMicPageAdapter);


        // onClick action...
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0 || tab.getPosition() == 1)
                    userMicPageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // swipe gesture action...
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }
}