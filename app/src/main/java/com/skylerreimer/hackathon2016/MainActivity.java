package com.skylerreimer.hackathon2016;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

        //TODO mp = MediaPlayer.create(getApplicationContext(), R.raw.audiofile);
        //TODO mp.start();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        //TODO
        // mp.stop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }
    public void closeApp(View view) {
        this.moveTaskToBack(true);
        MainActivity.this.finish();
    }

    public void startButton(View view) {
        if (Build.VERSION.SDK_INT > 19) {
            ViewGroup mSceneRoot = (ViewGroup)findViewById(R.id.scene_root);
            Scene diffScreen = Scene.getSceneForLayout(mSceneRoot, R.layout.difficulty_select, this);
            Transition fadeTransition = new Fade();
            TransitionManager.go(diffScreen, fadeTransition);
        } else {
            this.setContentView(R.layout.difficulty_select);
        }
    }

    public void extraButton(View view) {
        if (Build.VERSION.SDK_INT > 19) {
            ViewGroup mSceneRoot = (ViewGroup)findViewById(R.id.scene_root);
            Scene extrasScreen = Scene.getSceneForLayout(mSceneRoot, R.layout.extras, this);
            Transition fadeTransition = new Fade();
            TransitionManager.go(extrasScreen, fadeTransition);
        } else {
            this.setContentView(R.layout.difficulty_select);
        }
    }

    public void backButton(View view) {
        if (Build.VERSION.SDK_INT > 19) {
            ViewGroup mSceneRoot = (ViewGroup)findViewById(R.id.scene_root);
            Scene mainScreen = Scene.getSceneForLayout(mSceneRoot, R.layout.activity_main, this);
            Transition fadeTransition = new Fade();
            TransitionManager.go(mainScreen, fadeTransition);
        } else {
            this.setContentView(R.layout.difficulty_select);
        }
    }

    /**
     * Options menu button
     * @param view the current view
     */
    public void optionButton(View view) {
        if (Build.VERSION.SDK_INT > 19) {
            ViewGroup mSceneRoot = (ViewGroup)findViewById(R.id.scene_root);
            Scene optionScreen = Scene.getSceneForLayout(mSceneRoot, R.layout.options, this);
            Transition fadeTransition = new Fade();
            TransitionManager.go(optionScreen, fadeTransition);
        } else {
            this.setContentView(R.layout.difficulty_select);
        }
    }

    /**
     * Music option within the Options menu
     * @param view the current view
     */
    public void music(View view) {
        Button button = (Button)findViewById(R.id.musicButton);

        if (button.getText().equals("Music: ON")) {
            button.setText(R.string.musicOff);
            //// TODO: 12/26/2016
            //mp.pause();
        } else {
            button.setText(R.string.musicOn);
            //TODO
            //mp.start();
        }
    }

    public void sfx(View view) {
        Button button = (Button)findViewById(R.id.sfxButton);

        if (button.getText().equals("SFX: ON")) {
            button.setText(R.string.soundOff);
        } else {
            button.setText(R.string.soundOn);
        }
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameSetUp.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        this.startActivity(intent);

        //going back to main menu
        this.backButton(view);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}