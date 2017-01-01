package com.g11.g11reader.backend.effects;

import android.graphics.Canvas;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by H on 2017-01-01.
 */

public class QuitEffect implements Effect {
    @Override
    public void draw(Canvas canvas, MediaData data) {
    }

    @Override
    public Effect activate(MediaData data) {
        return this;
    }
}
