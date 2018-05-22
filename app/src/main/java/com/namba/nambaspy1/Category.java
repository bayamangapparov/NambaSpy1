package com.namba.nambaspy1;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bayaman on 5/21/18.
 */

public class Category {

   private String  title;
   private int image;

   public Category(){

   }

    public Category(String title, int image) {
        this.title = title;
        this.image = image;
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
}
