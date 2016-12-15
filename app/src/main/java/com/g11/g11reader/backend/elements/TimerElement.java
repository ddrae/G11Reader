package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

import java.util.Timer;

/**
 * Created by A on 2016-12-04.
 */

public class TimerElement implements Element {

    //TODO implement

    private Effect effect;

    public TimerElement(Effect effect) {
        this.effect = effect;
    }
    @Override
    public void update(long dt, MediaData data) {
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }
}
