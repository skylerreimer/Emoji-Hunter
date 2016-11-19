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
    private Paint color = new Paint();

    public DrawingBackground(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        topHalf.set(0,0,canvas.getWidth(), canvas.getHeight()/2);

        color.setColor(Color.GRAY);
        color.setStyle(Paint.Style.FILL);

        canvas.drawRect(topHalf, color);

        color.setColor(Color.argb(255, 249, 129, 0));
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/4, canvas.getWidth()/4,color);

        color.setTextSize(getStatusBarSize());
        int score = 0;
        canvas.drawText("Score: " + score, canvas.getWidth()/10,100,color);

        int timeLeft = 0;
        canvas.drawText("Time Left: "+timeLeft, 9*canvas.getWidth()/10,100,color );

        canvas.drawText("Find this Emoji",400,canvas.getHeight()/2 ,color);
    }
}
