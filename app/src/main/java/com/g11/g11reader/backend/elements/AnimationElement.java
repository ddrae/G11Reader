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

    public AnimationElement(int index) {
        this.index = index;
    }

    @Override
    public void update(long dt, MediaData data) {
        timepassed += dt;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
        Movie movie = data.getAnimation(index);
        movie.setTime(((int)timepassed) % movie.duration());

        //TODO draw movie

        movie.setTime(0);
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }
}
