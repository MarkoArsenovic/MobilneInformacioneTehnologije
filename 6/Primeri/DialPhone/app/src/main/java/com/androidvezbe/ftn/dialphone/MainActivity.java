package com.androidvezbe.ftn.dialphone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCallBtnClick(View view){
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall();
        }else {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                phoneCall();
            }else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(permissionGranted){
            phoneCall();
        }else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);

            EditText editTextCallNumber = (EditText) findViewById(R.id.callNumber);
            String txtNumber = editTextCallNumber.getText().toString().trim();
            number = "tel:" + txtNumber;
            callIntent.setData(Uri.parse(number));
            startActivity(callIntent);
        }else{
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

}
