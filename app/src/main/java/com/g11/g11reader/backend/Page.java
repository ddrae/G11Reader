package com.g11.g11reader.backend;

import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.g11.g11reader.backend.elements.SoundElement;
import com.g11.g11reader.backend.elements.TimerElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2016-12-04.
 */

public class Page {
    private List<Element> elements;

    private Integer nextPage;
    private Integer previousPage;
    private Integer music;

    public Page(List<Element> elements) {
        this.elements = elements;
    }

    public Page(List<Element> elements, Integer next, Integer previous, Integer music) {
        this.elements = elements;
        nextPage = next;
        previousPage = previous;
        this.music = music;
    }

    public List<Effect> update(long dt, MediaData data) {
        if(music!=null) {
            MediaPlayer mp = data.getSound(music);
            if(!mp.isPlaying()) {
                mp.setLooping(true);
                //mp.start();
            }
        }

        List<Effect> effects = new ArrayList<>();
        for(Element e : elements) {
            Effect eff = e.update(dt, data);
            if(eff != null)
                effects.add(eff);
        }
        return effects;
    }

    public void draw(Canvas canvas, MediaData data) {
        for(Element e : elements) {
            e.draw(canvas, data);
        }
    }

    public List<Effect> pressed(int x, int y, MediaData data) {
        List<Effect> effects = new ArrayList<>();
        for(Element e : elements) {
            Effect eff = e.press(x, y, data);
            if(eff != null)
                effects.add(eff);
        }
        return effects;
    }

    public void resetPage(MediaData data) {
        for(Element e : elements) {
            if(e instanceof TimerElement){
                ((TimerElement) e).reset();
            }
        }
        for(Element e : elements) {
            if(e instanceof SoundElement){
                ((SoundElement) e).reset(data);
            }
        }
        //todo should reset reset anything else than timer?
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public Integer getMusic() { return music; }
}
