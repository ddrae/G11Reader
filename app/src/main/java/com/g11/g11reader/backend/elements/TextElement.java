package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
    public void update(long dt, MediaData data) {
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawText(text, posX, posY, paint);
        //TODO does not support word-wrap. Check out the use of StaticLayout.
        //http://stackoverflow.com/questions/6756975/draw-multi-line-text-to-canvas
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }
}
