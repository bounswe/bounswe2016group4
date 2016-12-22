package com.knowwhatwoueat.kwue.DataModels;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 22.12.2016.
 */

public class RecommendedFoods {
    private String food_image;
    private int food_id;
    private double calorie_value;
    private double food_rate;
    private String food_name;
    private String food_owner;
    private String food_description;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_image() {
        return food_image;
    }

    public void setFood_image(String food_image) {
        this.food_image = food_image;
    }

    public double getCalorie_value() {
        return calorie_value;
    }

    public void setCalorie_value(double calorie_value) {
        this.calorie_value = calorie_value;
    }

    public double getFood_rate() {
        return food_rate;
    }

    public void setFood_rate(double food_rate) {
        this.food_rate = food_rate;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_owner() {
        return food_owner;
    }

    public void setFood_owner(String food_owner) {
        this.food_owner = food_owner;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }
}
