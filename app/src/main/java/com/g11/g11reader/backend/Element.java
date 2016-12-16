package com.g11.g11reader.backend;

import android.graphics.Canvas;

/**
 * Created by A on 2016-12-04.
 */

public interface Element {

    public Effect update(long dt, MediaData data);

    public void draw(Canvas canvas, MediaData data);

    public Effect press(int x, int y, MediaData data);
}
