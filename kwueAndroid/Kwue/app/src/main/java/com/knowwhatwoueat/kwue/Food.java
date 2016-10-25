package com.knowwhatwoueat.kwue;

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

    public Food(Server foodServer, String info, String name, List<String> ingredientList, Nutrition nutrition, List<String> tagList) {
        this.foodServer = foodServer;
        this.info = info;
        this.name = name;
        this.ingredientList = ingredientList;
        this.nutrition = nutrition;
        this.tagList = tagList;
    }
}
