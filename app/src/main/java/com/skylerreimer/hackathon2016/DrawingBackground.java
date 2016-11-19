package com.skylerreimer.hackathon2016;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Krishna Ganesan on 11/19/2016.
 */

public class DrawingBackground extends View {

    public DrawingBackground(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Rect ourRect = new Rect();
        ourRect.set(0,0,canvas.getWidth(), canvas.getHeight()/2);

        Paint color = new Paint();
        color.setColor(Color.GRAY);
        color.setStyle(Paint.Style.FILL);

        canvas.drawRect(ourRect, color);

        color.setColor(Color.argb(255,  249, 129, 0));
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/4, canvas.getWidth()/4,color);


        int textSize = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            textSize = getResources().getDimensionPixelSize(resourceId);
        }

        color.setTextSize(textSize);
        int score = 0;
        canvas.drawText("Score: " + score, canvas.getWidth()/10,100,color);

        int timeLeft = 0;
        canvas.drawText("Time Left: "+timeLeft, 9*canvas.getWidth()/10,100,color );

        canvas.drawText("Find this Emoji",400,canvas.getHeight()/2 ,color);
    }
}
