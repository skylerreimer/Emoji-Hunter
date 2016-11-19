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

        LinearLayout layout = new LinearLayout(this.getContext());

        TextView textView = new TextView(this.getContext());
        textView.setVisibility(View.VISIBLE);
        textView.setText("Hello world");
        layout.addView(textView);

        layout.measure(canvas.getWidth(), canvas.getHeight());
        layout.layout(0, 0, canvas.getWidth(), canvas.getHeight() / 2 );

        // To place the text view somewhere specific:
        canvas.translate(canvas.getWidth()/2, 0);

        layout.draw(canvas);

    }
}
