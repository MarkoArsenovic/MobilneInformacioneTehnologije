package com.androidvezbe.ftn.phonestatelistener;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String number) {
            String phoneState = number;
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    phoneState += "CALL_STATE_IDLE\n";
                case TelephonyManager.CALL_STATE_RINGING:
                    phoneState += "CALL_STATE_RINGING\n";
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    phoneState += "CALL_STATE_OFFHOOK\n";
            }

            Log.i("STATE",phoneState);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TelephonyManager telephonyManager =
                (TelephonyManager)
                        getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(mPhoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void dialPhoneClk(View view){
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

    public void phoneCall(){if (ActivityCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }
    }

}
