package com.admereselvyn.mic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Objects;

public class PaymentSummary extends AppCompatActivity {

    private final String[] descriptionData = {"Details", "Summary", "Payment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);

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

        Button proceed=findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentSummary.this,Payment.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}