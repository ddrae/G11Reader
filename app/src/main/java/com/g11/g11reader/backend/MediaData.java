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

    public Map<String, Boolean> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, Boolean> flags) {
        this.flags = flags;
    }

    public List<Bitmap> getImages() {
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public List<Movie> getAnimations() {
        return animations;
    }

    public void setAnimations(List<Movie> animations) {
        this.animations = animations;
    }

    public List<MediaPlayer> getSounds() {
        return sounds;
    }

    public void setSounds(List<MediaPlayer> sounds) {
        this.sounds = sounds;
    }
}
