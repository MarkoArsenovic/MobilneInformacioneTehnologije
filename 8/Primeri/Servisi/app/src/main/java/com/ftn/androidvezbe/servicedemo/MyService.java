package com.ftn.androidvezbe.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Marko on 18-Dec-17.
 */

public class MyService extends Service {

    //creating a mediaplayer object
    private MediaPlayer player;


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Servis pokrenut",
                Toast.LENGTH_LONG).show();

        //preuzimanje sistemskog default ringtone
        player = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);

        //kontinualno reprodukovanje zvuka
        player.setLooping(true);

        //pokretanje
        player.start();


        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Servis zaustavljen",
                Toast.LENGTH_LONG).show();

        //zaustavljanje player-a kada se unisti servis
        player.stop();
    }
}

