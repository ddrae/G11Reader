package com.g11.g11reader.backend;

import android.graphics.Canvas;

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

    public Page(List<Element> elements) {
        this.elements = elements;
    }

    public Page(List<Element> elements, Integer next, Integer previous) {
        this.elements = elements;
        nextPage = next;
        previousPage = previous;
    }

    public List<Effect> update(long dt, MediaData data) {
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

    public void resetPage() {
        for(Element e : elements) {
            if(e instanceof TimerElement){
                ((TimerElement) e).reset();
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
}
