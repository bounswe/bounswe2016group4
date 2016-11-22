package com.knowwhatwoueat.kwue.DataModels;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 25.10.2016.
 */

public class Food {

    private int food_id;
    private String food_name;
    private String food_image;
    private String time_added;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_image() {
        return food_image;
    }

    public void setFood_image(String food_image) {
        this.food_image = food_image;
    }

    public String getTime_added() {
        return time_added;
    }

    public void setTime_added(String time_added) {
        this.time_added = time_added;
    }
}
