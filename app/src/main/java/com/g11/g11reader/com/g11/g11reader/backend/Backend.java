package com.g11.g11reader.com.g11.g11reader.backend;

import android.graphics.Canvas;

/**
 * Created by Allea10 on 2016-11-30.
 */

public class Backend {
    private Canvas currentFrame;
    private BackendState state;

    public Backend(Book book) {
    }

    /**
     *
     * @param dt    time passed since last update in millisecs
     */
    public void update(long dt) {
    }

    public synchronized Canvas getFrame() {
        return null;
    }

    protected synchronized void setCurrentFrame(Canvas canvas) {
        currentFrame = canvas;
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
