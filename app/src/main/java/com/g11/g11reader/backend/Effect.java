package com.g11.g11reader.backend;

import android.graphics.Canvas;

/**
 * Created by A on 2016-12-11.
 */

public interface Effect {
    public void draw(Canvas canvas, MediaData data);
    public Effect activate(MediaData data);
}
