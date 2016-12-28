package com.g11.g11reader.backend.effects;

import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by A on 2016-12-11.
 */

public class PlaySoundEffect implements Effect {
    private int index;

    public PlaySoundEffect(int index) {
        this.index = index;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
    }

    @Override
    public Effect activate(MediaData data) {
        MediaPlayer mediaPlayer = data.getSound(index);
        mediaPlayer.start();
        return null;
    }
}
