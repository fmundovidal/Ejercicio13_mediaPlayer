package com.example.a5alumno.ejercicio13_mediaplayer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener {

    /*private static final String PROVIDER_AUTHORITY = "com.example.a5alumno.Ejercicio13_mediaPlayer";

    private static final String MUSIC_AUTHORITY = "/raw/bensoundbrazilsamba";
    private static final Uri MEDIA_URI = Uri.parse("content://" + PROVIDER_AUTHORITY + MUSIC_AUTHORITY);*/

    MediaPlayer mMediaPlayer = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlayTrack = (Button) this.findViewById(R.id.btnPlayTrack);
        btnPlayTrack.setOnClickListener(this);

        Button btnStopTrack = (Button) this.findViewById(R.id.btnStopTrack);
        btnStopTrack.setOnClickListener(this);

        Button btnPlaySound = (Button) this.findViewById(R.id.btnPlaySound);
        btnPlaySound.setOnClickListener(this);

        //this.mMediaPlayer = MediaPlayer.create(this, R.raw.bensoundbrazilsamba);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        AssetFileDescriptor afd = this.getResources().openRawResourceFd(R.raw.bensoundbrazilsamba);


        try {
            mMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            afd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.prepareAsync();


    }

    @Override
    public void onClick(View view) {

        Intent intentServ = new Intent(this, MyIntentService.class);

        switch (view.getId()) {
            case R.id.btnPlayTrack:
                mMediaPlayer.start();
                break;

            case R.id.btnStopTrack:
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                this.mMediaPlayer = MediaPlayer.create(this, R.raw.bensoundbrazilsamba);
                break;
            case R.id.btnPlaySound:
                /*mMediaPlayer.stop();

                mMediaPlayer.prepareAsync();*/
                break;

            case R.id.btnPlaySoundInService:

                startService(intentServ);
                break;

            default:
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
