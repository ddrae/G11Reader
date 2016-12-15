package com.g11.g11reader.backend.effects;

import android.graphics.Canvas;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by A on 2016-12-11.
 */

public class GoToPageEffect implements Effect {
    public final int index;

    public GoToPageEffect(int index) {
        this.index = index;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
    }

    @Override
    public Effect activate(MediaData data) {
        return this;
    }
}
