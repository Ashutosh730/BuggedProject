package com.admereselvyn.mic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.admereselvyn.mic.api.models.get_all_contests.Contest;

import java.util.List;


public class Activity_RegisterNow extends AppCompatActivity {

    private TextView contestName, startDate, endDate, fee;
    private Button registerNow;
    private ImageView bannerImage;
    private Contest contest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register_now);

        contestName = findViewById(R.id.register_now_contest_name);
        startDate = findViewById(R.id.register_now_startDate);
        endDate = findViewById(R.id.register_now_endDate);
        fee = findViewById(R.id.register_now_fees);
        registerNow = findViewById(R.id.btn_register_now);
        bannerImage = findViewById(R.id.register_now_bannerImg);

        contest = (Contest) getIntent().getSerializableExtra("ContestDetails");

        contestName.setText(contest.getName());
        startDate.setText(contest.getStartContest());
        endDate.setText(contest.getEndContest());
        fee.setText(contest.getFees()+"");

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });

    }

    private void openRegistrationActivity(){
        Intent i = new Intent(this, Activity_Submit.class);
        i.putExtra("ContestDetails", contest);
        startActivity(i);
        overridePendingTransition(0, 0);

    }
}