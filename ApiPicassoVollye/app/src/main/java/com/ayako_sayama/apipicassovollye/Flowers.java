package com.ayako_sayama.apipicassovollye;

/**
 * Created by ayako_sayama on 2017/02/21.
 */

public class Flowers {
    String name;
    String photo;

    public Flowers(String name, String photo) {
        this.photo = photo;
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
