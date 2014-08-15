package com.appvn.cocacola.model;

/**
 * Created by truongtvd on 7/18/14.
 */
public class Fonts {

    private String title;
    private String font;

    public Fonts(String title, String font) {
        this.title = title;
        this.font = font;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }
}
