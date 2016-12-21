package com.knowwhatwoueat.kwue.DataModels;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 21.12.2016.
 */

public class MonthlyConsumptionGraph {
    private int day_number;
    private double calorie_value;
    private double sugar_value;
    private double protein_value;
    private double carbohydrate_value;
    private double fat_value;

    public int getDay_number() {
        return day_number;
    }

    public void setDay_number(int day_number) {
        this.day_number = day_number;
    }

    public double getCalorie_value() {
        return calorie_value;
    }

    public void setCalorie_value(double calorie_value) {
        this.calorie_value = calorie_value;
    }

    public double getSugar_value() {
        return sugar_value;
    }

    public void setSugar_value(double sugar_value) {
        this.sugar_value = sugar_value;
    }

    public double getProtein_value() {
        return protein_value;
    }

    public void setProtein_value(double protein_value) {
        this.protein_value = protein_value;
    }

    public double getCarbohydrate_value() {
        return carbohydrate_value;
    }

    public void setCarbohydrate_value(double carbohydrate_value) {
        this.carbohydrate_value = carbohydrate_value;
    }

    public double getFat_value() {
        return fat_value;
    }

    public void setFat_value(double fat_value) {
        this.fat_value = fat_value;
    }
}
