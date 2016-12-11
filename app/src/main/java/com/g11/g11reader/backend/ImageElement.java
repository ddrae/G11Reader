package com.g11.g11reader.backend;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by A on 2016-12-04.
 */

public class ImageElement implements Element {
    public int index;
    public int posX;
    public int posY;


    @Override
    public void update(long dt, Canvas canvas, MediaData data) {
        // Paint paint = new Paint(data.getFlags()..);
        Paint paint = new Paint();

        // Will draw image upon image, use canvas.drawColor(Color.BLACK) to clear canvas. Most likely in... backend?
        canvas.drawBitmap(data.getImages().get(index), posX, posY, paint);
    }

    @Override
    public void press(int x, int y) {

    }
}
