package com.namba.nambaspy1;

/**
 * Created by bayaman on 5/22/18.
 */

public class Place {

    private  String title;
    private int image;
    private int rating;
    private String placeAdress;
    private String otzv;

    public Place(String title, int image  /*int rating, String placeAdress, String otzv */) {
        this.title = title;
        this.image = image;
//        this.rating = rating;
//        this.placeAdress = placeAdress;
//        this.otzv = otzv;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//
//    public String getPlaceAdress() {
//        return placeAdress;
//    }
//
//    public void setPlaceAdress(String placeAdress) {
//        this.placeAdress = placeAdress;
//    }
//
//    public String getOtzv() {
//        return otzv;
//    }
//
//    public void setOtzv(String otzv) {
//        this.otzv = otzv;
//    }
}
