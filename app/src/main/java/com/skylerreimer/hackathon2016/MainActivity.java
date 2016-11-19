package com.skylerreimer.hackathon2016;

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
        mp = MediaPlayer.create(getApplicationContext(), R.raw.audiofile);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mp.start();
        setContentView(R.layout.activity_main);
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
            button.setText("Music: OFF");
            mp.pause();
        } else {
            button.setText("Music: ON");
            mp.start();
        }
    }

    public void sfx(View view) {
        Button button = (Button) findViewById(R.id.sfxButton);

        if (button.getText().equals("Music: ON")) {
            button.setText("SFX: OFF");
            mp.pause();
        } else {
            button.setText("SFX: ON");
            mp.start();
        }
    }
}