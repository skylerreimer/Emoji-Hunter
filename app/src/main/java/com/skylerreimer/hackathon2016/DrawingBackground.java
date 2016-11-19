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

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        canvas.drawRect(ourRect, blue);

        blue.setColor(Color.argb(255,  249, 129, 0));
        blue.setTextSize(40);
        int score = 0;
        canvas.drawText("Score: " + score, 10,50,blue);



    }
}
