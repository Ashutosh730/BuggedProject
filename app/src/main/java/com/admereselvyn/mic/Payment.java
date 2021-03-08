package com.admereselvyn.mic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.Objects;

public class Payment extends AppCompatActivity {

    private final String[] descriptionData = {"Details", "Summary", "Payment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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

        RadioButton rbutton_gpay = findViewById(R.id.rbutton_gpay);
        RadioButton rbutton_upi = findViewById(R.id.rbutton_upi);
        RadioButton rbutton_card = findViewById(R.id.rbutton_card);
        RadioButton rbutton_netbanking = findViewById(R.id.rbutton_netbanking);

        rbutton_gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbutton_gpay.setChecked(true);
                rbutton_upi.setChecked(false);
                rbutton_card.setChecked(false);
                rbutton_netbanking.setChecked(false);
            }
        });

        rbutton_upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbutton_gpay.setChecked(false);
                rbutton_upi.setChecked(true);
                rbutton_card.setChecked(false);
                rbutton_netbanking.setChecked(false);
            }
        });

        rbutton_netbanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbutton_gpay.setChecked(false);
                rbutton_upi.setChecked(false);
                rbutton_card.setChecked(false);
                rbutton_netbanking.setChecked(true);
            }
        });

        rbutton_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbutton_gpay.setChecked(false);
                rbutton_upi.setChecked(false);
                rbutton_card.setChecked(true);
                rbutton_netbanking.setChecked(false);
            }
        });

    }
}