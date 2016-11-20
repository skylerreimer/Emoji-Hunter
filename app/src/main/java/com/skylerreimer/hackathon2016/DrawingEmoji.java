package com.skylerreimer.hackathon2016;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.view.View;
import android.content.Context;

import java.util.Map;

/**
 * Created by Krishna Ganesan on 11/19/2016.
 */

public class DrawingEmoji extends SurfaceView {

    Bitmap emojis;
    int currentXPosition;
    int currentYPosition;
    Map emojiList;

    public DrawingEmoji(Context context)
    {
        super(context);
        emojis = BitmapFactory.decodeResource(getResources(), R.drawable.emoji_spritesheet);
        EmojiGenerator emojiGen = new EmojiGenerator();
        emojiGen.PopulateList();
        emojiList = emojiGen.GetList();

        currentXPosition = 0;
        currentYPosition = 0;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int startingXPosition = canvas.getWidth() / 10;
        int startingYPosition = canvas.getHeight() * 5 /9;
        int verticalDistanceToNewRow = canvas.getHeight() / 12;
        int horizontalDistanceToNewColumn = canvas.getWidth() / 9;

        int index = 0;

        currentXPosition = startingXPosition;
        currentYPosition = startingYPosition;
        for (int row = 0; row <5; row++)
        {
            currentYPosition = startingYPosition + row * verticalDistanceToNewRow;

            for (int col = 0; col < 5; col++)
            {
                currentXPosition = startingXPosition + horizontalDistanceToNewColumn * col;
                Rect sourceRect = (Rect) emojiList.get(index);
                canvas.drawBitmap(emojis, sourceRect.left, sourceRect.top, new Paint());

                index++;
            }
        }



//        Rect ourRect = new Rect();
//        ourRect.set(0, 0, canvas.getWidth(), canvas.getHeight() / 2);
//
//        Paint blue = new Paint();
//        blue.setColor(Color.BLUE);
//        blue.setStyle(Paint.Style.FILL);
//
//        canvas.drawRect(ourRect, blue);


    }

//    @Override
//    public void run() {
//
//    }
}