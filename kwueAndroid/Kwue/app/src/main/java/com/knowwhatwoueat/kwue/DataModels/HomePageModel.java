package com.knowwhatwoueat.kwue.DataModels;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 22.12.2016.
 */

public class HomePageModel {
    private int user_type;
    private int user_id;
    private RecommendedFoods[] recommendations;

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public RecommendedFoods[] getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(RecommendedFoods[] recommendations) {
        this.recommendations = recommendations;
    }
}
