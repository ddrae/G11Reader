package com.g11.g11reader.backend;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2016-12-04.
 */

public class Page {
    private List<Element> elements;

    public Page(List<Element> elements) {
        this.elements = elements;
    }

    public void update(long dt, MediaData data) {
        for(Element e : elements) {
            e.update(dt, data);
        }
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
        //TODO reset timers back to 0 and such.
    }
}
