package com.knowwhatwoueat.kwue.DataModels;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 25.10.2016.
 */

public class Food {
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


}
