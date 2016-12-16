package com.g11.g11reader.fileinput;

import android.graphics.Bitmap;
import android.graphics.Movie;
import android.media.MediaPlayer;

import com.g11.g11reader.backend.Book;
import com.g11.g11reader.backend.MediaData;
import com.g11.g11reader.backend.Page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by H on 2016-12-01.
 */

public class BookLoader {
    private BookLoader() {}

    public static Book loadBook(File file) {
        List<Page> pages = new ArrayList<>();
        List<Bitmap> images = new ArrayList<>();
        List<Movie> animations = new ArrayList<>();
        List<MediaPlayer> sounds = new ArrayList<>();



        MediaData data = new MediaData();
        data.setImages(images);
        data.setAnimations(animations);
        data.setSounds(sounds);
        Book book = new Book(pages, data);
        return book;
    }
}
