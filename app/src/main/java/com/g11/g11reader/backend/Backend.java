package com.g11.g11reader.backend;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by A on 2016-11-30.
 */

public class Backend {
    private final static int SCREEN_WIDTH = 1280;
    private final static int SCREEN_HEIGHT = 800;

    private Bitmap currentFrame;
    private BackendState state;

    private Book book;

    public Backend(Book book) {
        this.book = book;
    }

    /**
     *
     * @param dt    time passed since last update in millisecs
     */
    public void update(long dt) {
        // UPDATE BOOK
        setState(BackendState.BUZY);
        book.update(dt);

        // DRAW CURRENT PAGE
        Bitmap frame = Bitmap.createBitmap(SCREEN_WIDTH, SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(frame);

        book.draw(canvas);

        setCurrentFrame(frame);

        // DONE
        setState(BackendState.READY);
    }

    public synchronized Bitmap getFrame() {
        return currentFrame;
    }

    protected synchronized void setCurrentFrame(Bitmap frame) {
        currentFrame = frame;
    }

    /**
     *
     * @param x     x-coordinate of press from 0 to 1 (left to right)
     * @param y     y-coordinate of press from 0 to 1 (top to bottom)
     */
    public void pressed(float x, float y) {
        book.pressed(Math.round(x*SCREEN_WIDTH), Math.round(y*SCREEN_HEIGHT));
    }

    public void swipedLeft() {
        book.nextPage();
    }

    public void swipedRight() {
        book.previousPage();
    }

    public synchronized BackendState getState() {
        return state;
    }

    public synchronized void setState(BackendState state) {
        this.state = state;
    }

    public enum BackendState {
        READY, BUZY, QUITTING
    }

}
