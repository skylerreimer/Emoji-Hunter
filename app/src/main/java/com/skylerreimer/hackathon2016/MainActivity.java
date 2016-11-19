package com.skylerreimer.hackathon2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
}
//test push comment