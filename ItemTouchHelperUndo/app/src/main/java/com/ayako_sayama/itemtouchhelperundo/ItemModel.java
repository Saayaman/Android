package com.ayako_sayama.itemtouchhelperundo;

/**
 * Created by ayako_sayama on 2017/02/28.
 */
public class ItemModel {
    String text;


    public ItemModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
