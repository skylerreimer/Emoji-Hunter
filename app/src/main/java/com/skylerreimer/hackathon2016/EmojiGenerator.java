package com.skylerreimer.hackathon2016;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Bitmap;
import java.lang.Object;
import java.util.Map;
import android.graphics.Rect;
import java.util.HashMap;

public class EmojiGenerator {



    private Map emojiList;

    public EmojiGenerator() {

        this.emojiList = new HashMap<Integer, Rect>();
    }

    public void PopulateList() {
        int emojiHeight = 88;
        int emojiWidth = 88;
        int startingRowXPosition = 248;
        int startingRowYPosition = 12;
        int verticalDistanceToNewRow = 122;
        int horizontalDistanceToNewColumn = 125;

        int currentXPosition = startingRowXPosition;
        int currentYPosition = startingRowYPosition;

        int index = 1;

        for (int row = 0; row < 5; row++) {
            currentYPosition = startingRowYPosition + row
                    * verticalDistanceToNewRow;
            currentXPosition = startingRowXPosition;

            for (int column = 0; column < 9; column++) {
                currentXPosition = startingRowXPosition + column
                        * horizontalDistanceToNewColumn;

                this.emojiList.put(index, new Rect(currentXPosition,
                        currentYPosition, emojiHeight, emojiWidth));
                index++;
            }
        }
    }

    public Map GetList()
    {
        return emojiList;
    }










}