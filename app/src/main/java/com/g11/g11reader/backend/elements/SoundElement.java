package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by A on 2016-12-04.
 */

public class SoundElement implements Element {
    private int index;

    //TODO Implement

    public SoundElement(int index) {
        this.index = index;
    }

    @Override
    public Effect update(long dt, MediaData data) {
        return null;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }
}
