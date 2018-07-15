package com.suli.suliapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.suli.suliapp.R;


public class AddMeasurementActivity extends AppCompatActivity {
    public static final String CUSTODY_CHAIN = "custodyChain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurement);
    }
}
