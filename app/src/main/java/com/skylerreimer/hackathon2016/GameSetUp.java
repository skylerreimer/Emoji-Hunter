package com.skylerreimer.hackathon2016;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class GameSetUp extends AppCompatActivity {
    //media player variable intialized to null
    private MediaPlayer mp = null;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super class to start
        super.onCreate(savedInstanceState);

        //setting up default window full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //drawing background and setting user view to this background

        game = new Game(this);

        this.setContentView(game);
    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first

        //get and start music for game
        //mp = MediaPlayer.create(getApplicationContext(), R.raw.audiofile);
        //TODO
        // mp.start();

        game.startGame();
    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first

        //stop game music
        //TODO
        // mp.stop();
    }
}
