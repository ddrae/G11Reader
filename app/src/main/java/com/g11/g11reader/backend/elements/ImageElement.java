package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by A on 2016-12-04.
 */

public class ImageElement implements Element {
    private int index;
    private int posX;
    private int posY;

    public ImageElement(int index, int posX, int posY) {
        this.index = index;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public Effect update(long dt, MediaData data) {
        return null;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
        Paint paint = new Paint();
        canvas.drawBitmap(data.getImage(index), posX, posY, paint);
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }
}