package com.g11.g11reader.backend;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by A on 2016-12-04.
 */

public class MediaData {
    private List<Bitmap> images = new ArrayList<>();
    private List<Movie> animations = new ArrayList<>();
    private List<MediaPlayer> sounds = new ArrayList<>();
    private Map<String, Boolean> flags = new HashMap<>();

    public MediaData() {
    }

    public Boolean getFlag(String name) {
        return flags.get(name);
    }

    public void setFlag(String name, Boolean value) {
        this.flags.put(name, value);
    }

    public Bitmap getImage(int index) {
        return images.get(index);
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public Movie getAnimation(int index) {
        return animations.get(index);
    }

    public void setAnimations(List<Movie> animations) {
        this.animations = animations;
    }

    public MediaPlayer getSound(int index) {
        return sounds.get(index);
    }

    public void setSounds(List<MediaPlayer> sounds) {
        this.sounds = sounds;
    }
}
