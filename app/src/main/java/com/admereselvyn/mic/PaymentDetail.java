package com.admereselvyn.mic;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Objects;

public class PaymentDetail extends AppCompatActivity {
    private final String[] descriptionData = {"Details", "Summary", "Payment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);

//        ActionBar action = getSupportActionBar();
//        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
//        assert action != null;
//        Toolbar toolbar=(Toolbar)action.getCustomView().getParent();
//        toolbar.setContentInsetsAbsolute(0, 0);
//        toolbar.getContentInsetEnd();
//        toolbar.setPadding(0, 0, 0, 0);

        StateProgressBar stateProgressBar = findViewById(R.id.stateProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        TextView name = findViewById(R.id.name);
        String first = "Enter your Name";
        String next = "<font color='#EE0000'> *</font>";
        name.setText(Html.fromHtml(first + next));

        TextView email = findViewById(R.id.email);
        first = "Your Email Address";
        next = "<font color='#EE0000'> *</font>";
        email.setText(Html.fromHtml(first + next));

        TextView mobile = findViewById(R.id.mobile);
        first = "Your Mobile number";
        next = "<font color='#EE0000'> *</font>";
        mobile.setText(Html.fromHtml(first + next));

        Button proceed=findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentDetail.this, PaymentSummary.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}