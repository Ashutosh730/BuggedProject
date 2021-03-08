package com.admereselvyn.mic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainScreen extends AppCompatActivity {


    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        bottomNavigation=findViewById(R.id.bottomNavView);

        bottomNavigation.setBackground(null);
        // bottomNavigation.getMenu().getItem(2).setEnabled(false);


        //Changing Fragment after selecting id from BottomNavigationBar
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
         /*      if(item.getItemId()==R.id.buttonAppBarStat)
               {
                   fragment = new UserStatistics();
               }        */
                if(item.getItemId()==R.id.buttonAppBarProfile)
                {
                    fragment = new UserProfile();
                }
                else if(item.getItemId()==R.id.buttonAppBarMic)
                {
                    fragment=new UserMic();
                }
                else if(item.getItemId()==R.id.buttonAppBarHome)
                {
                    fragment=new UserHomePage();
                }
                else if(item.getItemId()==R.id.buttonAppBarMenu)
                {
                    fragment=new UserMenu();
                }

                return  loadFragment(fragment);
            }
        });

        if (savedInstanceState == null) {
            bottomNavigation.setSelectedItemId(R.id.buttonAppBarHome); //Default fragment when activity will create
        }


    }
    //This method will load particular Fragment
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            FrameLayout fragment_container = (FrameLayout) findViewById(R.id.fragment_container);
            fragment_container.removeAllViews();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container,fragment);
            transaction.commit();
            return true;
        }
        return false;
    }
}