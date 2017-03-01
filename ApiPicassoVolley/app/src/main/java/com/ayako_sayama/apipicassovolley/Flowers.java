package com.ayako_sayama.apipicassovolley;

/**
 * Created by ayako_sayama on 2017/02/21.
 */

public class Flowers {
    String name;
    String photo;
    double price;


    public Flowers(String name, String photo, double price) {
        this.photo = photo;
        this.name = name;
        this.price = price;

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


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
