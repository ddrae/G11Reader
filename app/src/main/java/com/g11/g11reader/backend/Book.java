package com.g11.g11reader.backend;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.media.MediaPlayer;

import java.util.List;

/**
 * Created by H on 2016-12-01.
 */

public class Book {
    private List<Bitmap> images;
    private List<Movie> animations;
    private List<MediaPlayer> sounds;
    private Page pages;

    Book() {
        pages = new Page();
    }
}
