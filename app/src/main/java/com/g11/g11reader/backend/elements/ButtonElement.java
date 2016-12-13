package com.g11.g11reader.backend.elements;

import android.graphics.Canvas;

import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.MediaData;

/**
 * Created by A on 2016-12-04.
 */

public class ButtonElement implements Element {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Effect effect;

    public ButtonElement(int posX, int posY, int width, int height, Effect effect) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.effect = effect;
    }

    @Override
    public void update(long dt, MediaData data) {
    }

    @Override
    public void draw(Canvas canvas, MediaData data) {
        effect.draw(canvas, data);
    }

    @Override
    public Effect press(int x, int y, MediaData data) {
        if((posX <= x)
                &&(posY <= y)
                &&(posX+width >= x)
                &&(posY+height >= y)) {
            return effect.activate();
        }
        return null;
    }
}
