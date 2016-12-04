package com.g11.g11reader.backend;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by A on 2016-11-30.
 */

public class Backend {
    private Bitmap currentFrame;
    private BackendState state;

    public Backend(Book book) {
    }

    /**
     *
     * @param dt    time passed since last update in millisecs
     */
    public void update(long dt) {
    }

    public synchronized Bitmap getFrame() {
        return null;
    }

    protected synchronized void setCurrentFrame(Bitmap frame) {
        currentFrame = frame;
    }

    /**
     *
     * @param x     x-coordinate of press from 0 to 1 (left to right)
     * @param y     y-coordinate of press from 0 to 1 (bottom to top?)
     */
    public void pressed(float x, float y) {
    }

    public void swipedLeft() {
    }

    public void swipedRight() {
    }

    public BackendState getState() {
        return state;
    }

    public enum BackendState {
        READY, BUZY, QUITTING
    }

}
