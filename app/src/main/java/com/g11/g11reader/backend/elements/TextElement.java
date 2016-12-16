package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by H on 2016-12-13.
 */

public class TextElement implements Element {
    private String text;
    private int posX;
    private int posY;

    public TextElement(String text, int posX, int posY) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public Effect update(long dt, MediaData data) {
        return null;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(200);
        StaticLayout textLayout = new StaticLayout(text, textPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate(posX, posY);
        textLayout.draw(canvas);
        canvas.restore();
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }
}
