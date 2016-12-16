package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.widget.ArrayAdapter;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by A on 2016-12-04.
 */

public class AnimationElement implements Element {
    private int index;
    private long timepassed = 0;
    private float x;
    private float y;

    public AnimationElement(int index, float x, float y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    @Override
    public Effect update(long dt, MediaData data) {
        timepassed += dt;
        return null;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
        Movie movie = data.getAnimation(index);
        movie.setTime(((int)timepassed) % movie.duration());
        movie.draw(canvas, x, y);

        movie.setTime(0);
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }
}
