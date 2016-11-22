package com.knowwhatwoueat.kwue.DataModels;

/**
 * Created by Cagla Aksoy on 22.11.2016.
 */

public class FoodServerBasicSearch {

    private String user_image;
    private int user_id;
    private String user_name;

    public FoodServerBasicSearch(String user_image, int user_id, String user_name) {
        this.user_image = user_image;
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public FoodServerBasicSearch(){

    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String imageUrl) {
        this.user_image = imageUrl;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int id) {
        this.user_id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String name) {
        this.user_name = name;
    }
}