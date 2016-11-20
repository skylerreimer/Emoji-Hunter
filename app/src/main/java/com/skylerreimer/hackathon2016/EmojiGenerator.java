package com.skylerreimer.hackathon2016;

import android.graphics.Canvas;
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
    private Canvas canvas;

    public EmojiGenerator(Canvas myCanvas) {

        canvas = myCanvas;
        this.emojiList = new HashMap<Integer, Rect>();
        PopulateList();
    }

    public void PopulateList() {
        int emojiHeight = 88;
        int emojiWidth = 87;
        int startingRowXPosition = 122;
        int startingRowYPosition = 12;
        int verticalDistanceToNewRow = 120;
        int horizontalDistanceToNewColumn = 126;

        int currentXPosition = startingRowXPosition;
        int currentYPosition = startingRowYPosition;

        int index = 0;

        for (int row = 0; row < 5; row++) {
            currentYPosition = startingRowYPosition + row
                    * verticalDistanceToNewRow;

            for (int column = 0; column <= 8; column++) {
                currentXPosition = startingRowXPosition + column
                        * horizontalDistanceToNewColumn;

                this.emojiList.put(index, new Rect(currentXPosition,
                        currentYPosition, currentXPosition + emojiWidth, currentYPosition + emojiHeight));
                index++;
            }
        }
    }

    public Map GetList()
    {
        return emojiList;
    }










}