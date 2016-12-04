package com.g11.g11reader.backend;

import android.graphics.Canvas;

/**
 * Created by A on 2016-12-04.
 */

public interface Element {

    public void update(long dt, Canvas canvas, MediaData data);

    public void press(int x, int y);
}
