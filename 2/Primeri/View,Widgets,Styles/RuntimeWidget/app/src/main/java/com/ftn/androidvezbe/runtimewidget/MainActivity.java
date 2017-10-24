package com.ftn.androidvezbe.runtimewidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        DatePicker datePicker = new DatePicker(this);
        layout.addView(datePicker);
    }
}
