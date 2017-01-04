package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

import java.io.IOException;

/**
 * Created by A on 2016-12-04.
 */

public class SoundElement implements Element {
    private int index;

    private boolean hasPlayed = false;

    public SoundElement(int index) {
        this.index = index;
    }

    @Override
    public Effect update(long dt, MediaData data) {
        MediaPlayer mp = data.getSound(index);
        if(!hasPlayed) {
            mp.prepareAsync();
            hasPlayed = true;
        }
        return null;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }

    public void reset(MediaData data) {
        MediaPlayer mp = data.getSound(index);
        mp.stop();
        hasPlayed = false;
    }
}
