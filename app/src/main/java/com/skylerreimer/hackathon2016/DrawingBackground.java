package com.skylerreimer.hackathon2016;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import java.util.Map;
import java.util.Random;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.MotionEvent;


public class DrawingBackground extends View {

    Bitmap emojis ;
    Bitmap emojisCopy;
    Canvas c2;
    Paint pTouch;
    Rect finalRect;
    int offScreen = -100;
    int x = offScreen;
    int y = offScreen;
    private Rect topHalf = new Rect();
    private Paint centerText = new Paint(), timeText = new Paint(), scoreText = new Paint();


    private int getStatusBarSize() {

        int textSize = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            textSize = getResources().getDimensionPixelSize(resourceId);
        }
        return textSize;
    }



    public DrawingBackground(Context context)
    {
        super(context);


        emojis =  BitmapFactory.decodeResource(getResources(), R.drawable.emoji_spritesheet);
        emojisCopy =  BitmapFactory.decodeResource(getResources(), R.drawable.emoji_spritesheet).copy(Bitmap.Config.ARGB_8888, true);

        c2 = new Canvas(emojisCopy);

        pTouch = new Paint(Paint.ANTI_ALIAS_FLAG);
        pTouch.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));
        pTouch.setColor(Color.TRANSPARENT);
        pTouch.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));


    }


    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {


        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                //highlight choice
                System.out.println("Press Detected");
                x = (int) ev.getX();
                y = (int) ev.getY();

                ObserveTouchAction(finalRect);
                break;
            }

        }

        return true;
    }

    public void ObserveTouchAction (Rect correctRect)
    {
       // c2.drawBitmap(emojisCopy, 0,0, null);

        if (x <= correctRect.right && x >= correctRect.left && y<= correctRect.bottom && y >= correctRect.top)
        {

            invalidate();
            /*if ( x and y are inside the destination rectangle of the correct emoji
                   call stuff to score,
                   add time,
                   display message popup saying you were right or something

                    finally after these actions are done
                    invalidate();

             */




            int radiusSize = 50;
           // c2.drawCircle(x, y, radiusSize, pTouch);
           // c2.drawBitmap(emojis, 0, 0, null);
        }


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);




        topHalf.set(0,0,canvas.getWidth(), canvas.getHeight()/2);

        centerText.setColor(Color.GRAY);
        centerText.setStyle(Paint.Style.FILL);

        canvas.drawRect(topHalf, centerText);

        timeText.setColor(Color.argb(255, 249, 129, 0));
        scoreText.setColor(Color.argb(255, 249, 129, 0));
        centerText.setColor(Color.argb(255, 249, 129, 0));

        int textSize = getStatusBarSize();
        centerText.setTextSize(textSize);
        timeText.setTextSize(textSize);
        scoreText.setTextSize(textSize);

        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/4, canvas.getWidth()/4, centerText);

        scoreText.setTextAlign(Paint.Align.LEFT);
        int score = 0;
        canvas.drawText("Score: " + score, 0 ,textSize, scoreText);

        timeText.setTextAlign(Paint.Align.RIGHT);
        int timeLeft = 9;
        if (timeLeft >= 10) {
            canvas.drawText("Time Left: "+ timeLeft, canvas.getWidth(), textSize, timeText);
        } else {
            canvas.drawText("Time Left: 0" + timeLeft, canvas.getWidth(),textSize, timeText);
        }

        centerText.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Find this Emoji",canvas.getWidth()/2, canvas.getHeight()/2 - textSize/2 , centerText);
        DrawEmojis(canvas);



    }


    public int SelectEmoji(Map list)
    {

        Random random = new Random();
        int indexOfEmoji = random.nextInt(list.size());

        return indexOfEmoji;
    }

    public int SelectIndex(int difficulty)
    {

        Random random = new Random();

        int locationOfEmoji = random.nextInt(difficulty);
        return locationOfEmoji;
    }

    public void DrawSelectedEmojiUpTop(Canvas canvas, Map emojisList, int indexOfEmoji)
    {
        Rect sourceRect = (Rect)emojisList.get(indexOfEmoji);



        int left = canvas.getWidth() / 4 ;
        int top = canvas.getHeight() /4 - canvas.getWidth() / 4 ;
        int right = canvas.getWidth() *  3 / 4;

        int bottom = canvas.getHeight() / 4 + canvas.getWidth() / 4 ;


        Rect destinationRectangle = new Rect(left, top, right, bottom);
        canvas.drawBitmap(emojis, sourceRect, destinationRectangle, new Paint());


    }

    public void DrawEmojis(Canvas canvas)
    {
        EmojiGenerator emojiGen = new EmojiGenerator();
        emojiGen.PopulateList();
        Map emojiList = emojiGen.GetList();

        int difficulty = 16;
        int startingXPosition = 0;
        int startingYPosition = canvas.getHeight() / 2;
        int verticalDistanceToNewRow = canvas.getHeight() / 8;
        int horizontalDistanceToNewColumn = canvas.getWidth() / 4;


        int currentXPosition ;
        int currentYPosition;

        Random random = new Random();


        int chosenEmoji = SelectEmoji(emojiList) ;

        int chosenIndex = SelectIndex(difficulty);

        int currentIndex = 0;

        Rect returnRect = new Rect();

        DrawSelectedEmojiUpTop(canvas, emojiList, chosenEmoji );

        for (int row = 0; row < 4; row++) {
            currentYPosition = startingYPosition + row * verticalDistanceToNewRow;

            for (int col = 0; col < 4; col++) {

                Rect sourceRect;
                if (currentIndex == chosenIndex)
                {
                     sourceRect = (Rect) emojiList.get(chosenEmoji);

                }

                else {
                    int randomEmojiToDisplay = random.nextInt(emojiList.size());
                    while (randomEmojiToDisplay == chosenEmoji) {
                        randomEmojiToDisplay = random.nextInt(emojiList.size());
                    }

                     sourceRect = (Rect) emojiList.get(randomEmojiToDisplay);


                }

                currentXPosition = startingXPosition + horizontalDistanceToNewColumn * col;
                Rect destinationRect = new Rect(currentXPosition, currentYPosition, currentXPosition + horizontalDistanceToNewColumn, currentYPosition + verticalDistanceToNewRow);
                if (currentIndex == chosenIndex)
                {
                    returnRect = destinationRect;
                }
                canvas.drawBitmap(emojis, sourceRect, destinationRect, new Paint());
                currentIndex++;
            }
        }

        finalRect = returnRect;
    }
}
