package com.skylerreimer.hackathon2016;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.content.Context;




public class DrawingBackground extends View {

    private int getStatusBarSize() {

        int textSize = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            textSize = getResources().getDimensionPixelSize(resourceId);
        }
        return textSize;
    }

    private Rect topHalf = new Rect();
    private Paint centerText = new Paint(), timeText = new Paint(), scoreText = new Paint();

    public DrawingBackground(Context context)
    {
        super(context);
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

    }
}
