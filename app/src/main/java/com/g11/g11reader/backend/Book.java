package com.g11.g11reader.backend;

import android.graphics.Canvas;

import com.g11.g11reader.backend.effects.GoToPageEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H on 2016-12-01.
 */

public class Book {
    private List<Page> pages = new ArrayList<>();
    private MediaData data = new MediaData();

    private int currentPage = 0;

    public Book() {
        pages.add(new Page(new ArrayList<Element>()));
    }

    public Book(List<Page> pages, MediaData data) {
        this.pages = pages;
        this.data = data;
    }

    public Page getPage(int index) {
        return pages.get(index);
    }

    public MediaData getData() {
        return data;
    }

    public synchronized void update(long dt) {
        Page page = pages.get(currentPage);
        page.update(dt, data);
    }

    public synchronized void draw(Canvas canvas) {
        Page page = pages.get(currentPage);
        page.draw(canvas, data);
    }

    public synchronized void pressed(int x, int y) {
        Page page = pages.get(currentPage);
        List<Effect> effects = page.pressed(x, y, data);
        for(Effect e : effects) {
            if(e instanceof GoToPageEffect) {
                page.resetPage();
                currentPage = ((GoToPageEffect) e).index;
                page.update(0, data);
            }
        }
    }
}
