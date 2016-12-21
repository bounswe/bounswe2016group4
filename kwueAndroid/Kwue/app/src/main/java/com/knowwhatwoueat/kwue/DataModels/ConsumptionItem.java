package com.knowwhatwoueat.kwue.DataModels;

import java.util.ArrayList;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 22.11.2016.
 */

public class ConsumptionItem {
    private ArrayList<Food> foods;
    private Nutrition nutritional_values_dict;
    private MonthlyConsumptionGraph[] graph_dict;

    public ConsumptionItem(){
        foods = new ArrayList<>();
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public Nutrition getNutritional_values_dict() {
        return nutritional_values_dict;
    }

    public void setNutritional_values_dict(Nutrition nutritional_values_dict) {
        this.nutritional_values_dict = nutritional_values_dict;
    }

    public MonthlyConsumptionGraph[] getGraph_dict() {
        return graph_dict;
    }

    public void setGraph_dict(MonthlyConsumptionGraph[] graph_dict) {
        this.graph_dict = graph_dict;
    }
}
