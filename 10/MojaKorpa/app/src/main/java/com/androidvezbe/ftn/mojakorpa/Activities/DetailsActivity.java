package com.androidvezbe.ftn.mojakorpa.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidvezbe.ftn.mojakorpa.R;

public class DetailsActivity extends AppCompatActivity {

    private int groceryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //TODO: podaci o stavci prosledjeni sa liste
        /*
        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            itemName.setText(bundle.getString("name"));
            quantity.setText(bundle.getString("quantity"));
            dateAdded.setText(bundle.getString("date"));
            groceryId = bundle.getInt("id");
        }
      */

    }

}
