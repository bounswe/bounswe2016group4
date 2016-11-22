package com.knowwhatwoueat.kwue.DataModels;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 25.10.2016.
 */

public class Food {

    private String food_image;
    private String food_name;
    private int food_id;
    private double calorie_value;


    public Food(String food_image, String food_name, int food_id, double calorie_value) {
        this.food_image = food_image;
        this.food_name = food_name;
        this.food_id = food_id;
        this.calorie_value = calorie_value;
    }

    public Food(){

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
    /*

    private long id;

    private String name;
    private Server foodServer;
    private String info;
    private List<String> ingredientList;
    private Nutrition nutrition;
    private double avarageRating;
    private List<String> commentList;
    private List<String> tagList;


    private String imageUrl;

    public Food(Server foodServer, String info, String name, List<String> ingredientList, Nutrition nutrition, List<String> tagList , String imageUrl) {
        this.foodServer = foodServer;
        this.info = info;
        this.name = name;
        this.ingredientList = ingredientList;
        this.nutrition = nutrition;
        this.tagList = tagList;
        this.imageUrl = imageUrl;
    }
    public Food(){

    }












    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<String> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Server getFoodServer() {
        return foodServer;
    }

    public void setFoodServer(Server foodServer) {
        this.foodServer = foodServer;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    public double getAvarageRating() {
        return avarageRating;
    }

    public void setAvarageRating(double avarageRating) {
        this.avarageRating = avarageRating;
    }

    */
}
