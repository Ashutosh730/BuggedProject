package com.admereselvyn.mic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.admereselvyn.mic.api.models.get_all_contests.Contest;
import com.google.android.material.textfield.TextInputEditText;

public class Activity_Submit extends AppCompatActivity {

    private TextInputEditText name, email, contactNumber;
    private Button submit;
    private TextView contestName;
    private Contest contest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__submit);

        name = findViewById(R.id.submit_name);
        email = findViewById(R.id.submit_email);
        contactNumber = findViewById(R.id.submit_contactNumber);
        submit = findViewById(R.id.btn_submit);
        contestName = findViewById(R.id.submit_contestName);

        contest = (Contest) getIntent().getSerializableExtra("ContestDetails");

        contestName.setText(contest.getName());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationActivity();
            }
        });
    }

    private void openRegistrationActivity(){
        Intent i = new Intent(this, Activity_UploadFile.class);
        i.putExtra("ContestDetails", contest);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
}