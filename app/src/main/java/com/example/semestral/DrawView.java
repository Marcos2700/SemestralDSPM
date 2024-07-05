package com.example.semestral;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DrawView extends View {

    private Paint paint;
    private ArrayList<Line> lines;

    public DrawView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        lines = new ArrayList<>();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (Line line : lines) {
            canvas.drawLine(line.startX, line.startY, line.endX, line.endY, paint);
        }
    }

    public void addLine(float startX, float startY, float endX, float endY) {
        lines.add(new Line(startX, startY, endX, endY));
        invalidate();
    }
}