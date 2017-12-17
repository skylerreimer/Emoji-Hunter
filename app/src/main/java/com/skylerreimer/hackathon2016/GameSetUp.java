package com.skylerreimer.hackathon2016;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class GameSetUp extends AppCompatActivity {
    private Game game;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super class to start
        super.onCreate(savedInstanceState);

        //setting up default window full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //drawing background and setting user view to this background
        this.game = new Game(this);

        //get data from the parent intent
        Intent parentIntent = getIntent();
        this.game.setDifficulty(parentIntent.getIntExtra("DIFFICULTY", -20));
        setHighScore(parentIntent.getIntExtra("SCORE", -20));

        this.setContentView(this.game);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //start the game
        play();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void finish(){
        //sending back high score to parent intent
        Intent output = new Intent();

        //printint score output to log for testing
        Log.d("SCORE", "" + this.game.getScore());

        MediaPlayer mp = MediaPlayer.create(this, R.raw.end);
        mp.start();

        //checking if game score is > high score and returning the higher of the 2
        if(this.game.getScore() > getHighScore()){
            output.putExtra("HSCORE", this.game.getScore());
        }else{
            output.putExtra("HSCORE", getHighScore());
        }

        //setting output intent to be returned to the parent intent
        setResult(RESULT_OK, output);

        super.finish();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void play(){
        //starting the game
        this.game.startGame();

        //game music
        //TODO
        // mp.stop();
    }

    //set the high score
    private void setHighScore(int score){
        this.score = score;
    }

    //get the high score
    public int getHighScore(){
        return this.score;
    }
}
