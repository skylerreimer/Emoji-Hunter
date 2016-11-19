package com.skylerreimer.hackathon2016;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    MediaPlayer mp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game);
    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first

        mp = MediaPlayer.create(getApplicationContext(), R.raw.audiofile);
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        mp.stop();
    }
}
