package com.skylerreimer.hackathon2016;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
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


    public void closeApp(View view){
        moveTaskToBack(true);
        MainActivity.this.finish();
    }

    public void startButton(View view){
        setContentView(R.layout.difficulty_select);
    }

    public void extraButton(View view) {
        setContentView(R.layout.extras);
    }

    public void optionButton(View view) {
        setContentView(R.layout.options);
    }

    public void backButton(View view){
        setContentView(R.layout.activity_main);
    }

    public void music(View view) {
        Button button = (Button) findViewById(R.id.musicButton);

        if (button.getText().equals("Music: ON")) {
            button.setText(R.string.musicOff);
            mp.pause();
        } else {
            button.setText(R.string.musicOn);
            mp.start();
        }
    }

    public void sfx(View view) {
        Button button = (Button) findViewById(R.id.sfxButton);

        if (button.getText().equals("SFX: ON")) {
            button.setText(R.string.soundOff);
        } else {
            button.setText(R.string.soundOn);
        }
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        startActivity(intent);
    }

}