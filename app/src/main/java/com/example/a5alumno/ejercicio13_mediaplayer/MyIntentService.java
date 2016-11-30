package com.example.a5alumno.ejercicio13_mediaplayer;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class MyIntentService extends IntentService {

    MediaPlayer mMediaPlayer = new MediaPlayer();

    private static final String TAG_STARTED_SERVICE = "In-StartedService";
    public MyIntentService(){
        super(MyIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        this.mMediaPlayer = MediaPlayer.create(this, R.raw.bensoundbrazilsamba);
        mMediaPlayer.start();


    }
}
