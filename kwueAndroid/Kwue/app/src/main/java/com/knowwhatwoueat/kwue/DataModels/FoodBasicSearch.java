package com.knowwhatwoueat.kwue.DataModels;

/**
 * Created by Cagla Aksoy on 22.11.2016.
 */

public class FoodBasicSearch {

    private String food_image;
    private String food_name;
    private int food_id;
    private double calorie_value;


    public FoodBasicSearch(String food_image, String food_name, int food_id, double calorie_value) {
        this.food_image = food_image;
        this.food_name = food_name;
        this.food_id = food_id;
        this.calorie_value = calorie_value;
    }

    public String getFood_image() {
        return food_image;
    }

    public void setFood_image(String imageUrl) {
        this.food_image = imageUrl;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String name) {
        this.food_name = name;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int id) {
        this.food_id = id;
    }

    public double getCalorie_value() {
        return calorie_value;
    }

    public void setCalorie_value(Double calorie_value) {
        this.calorie_value = calorie_value;
    }
}
