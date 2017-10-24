package com.ftn.androidvezbe.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Marko on 06-Nov-16.
 */

public class CustomView extends View {

    final Paint mPaint = new Paint();

    public CustomView(Context context) {
        super(context);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.DKGRAY);
        canvas.drawText("Custom View Text", 100, 100, mPaint);
    }
}+