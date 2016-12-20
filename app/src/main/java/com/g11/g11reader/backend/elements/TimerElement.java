package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by A on 2016-12-04.
 */

public class TimerElement implements Element {

    //TODO implement

    private Effect effect;
    private int seconds;
    private Timer timer;

    public TimerElement(Effect effect, int seconds) {
        this.effect = effect;
        this.seconds = seconds;
        timer = new Timer();
    }
    @Override
    public Effect update(long dt, MediaData data) {
        return null;
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        return null;
    }

    public void reset(){
        timer.cancel();
    }

    public void startTimer(MediaData data){
        //Todo dont know from where this method should be called.
        timer.schedule(new Task(data),seconds *1000);
    }

    private class Task extends TimerTask{

        private MediaData data;

        public Task(MediaData data){

            this.data = data;
        }

        @Override
        public void run() {
            effect.activate(data);
        }
    }
}
