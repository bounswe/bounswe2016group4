package com.knowwhatwoueat.kwue.DataModels;

import java.util.ArrayList;

/**
 * Created by Cagla Aksoy on 23.11.2016.
 */

public class GetFoodProfileResult {

    private double serving_weight_grams;
    private double protein_value;
    private double calorie_value;
    private double fiber_value;
    private int food_owner_id;
    private String food_recipe;
    private String food_description;
    private String food_image;
    private String food_name;
    private double fat_value;
    private double sugar_value;
    private double food_rate;
    private int food_id;
    private double carbohydrate_value;
    private double phosphorus;
    private double zinc;
    private double vitamin_B6;
    private double folatem;
    private double selenium;
    private double sodium_Na;
    private double vitamin_K;
    private double magnesium;
    private double pantothenic_acid;
    private double niacin;
    private double thiamin;
    private double copper;
    private double choline;
    private double vitamin_B12;
    private double riboflavin;
    private double manganese;
    private double vitamin_A;
    private int food_rate_count;
    private double flouride;
    private double iron_Fe;
    private double vitamin_C;
    private double vitamin_D;
    private double calcium;
    private double vitamin_E;
    private Tag[] tag_list;
    private boolean eaten = false;


    public GetFoodProfileResult(double serving_weight_grams, double protein_value, double calorie_value, double fiber_value,
                                int food_owner_id, String food_recipe, String food_description, String food_image, String food_name,
                                double fat_value, double sugar_value, double food_rate, int food_id, double carbohydrate_value, double phosphorus,
                                double zinc, double vitamin_B6, double folatem, double selenium, double sodium_Na, double vitamin_K,
                                double magnesium, double pantothenic_acid, double niacin, double thiamin, double copper,
                                double choline, double vitamin_B12, double riboflavin, double manganese, double vitamin_A, int food_rate_count,
                                double flouride, double iron_Fe, double vitamin_C, double vitamin_D, double calcium, double vitamin_E, Tag[] tag_list) {


        this.serving_weight_grams = serving_weight_grams;
        this.protein_value = protein_value;
        this.calorie_value = calorie_value;
        this.fiber_value = fiber_value;
        this.food_owner_id = food_owner_id;
        this.food_recipe = food_recipe;
        this.food_description = food_description;
        this.food_image = food_image;
        this.food_name = food_name;
        this.fat_value = fat_value;
        this.sugar_value = sugar_value;
        this.food_rate = food_rate;
        this.food_id = food_id;
        this.carbohydrate_value = carbohydrate_value;
        this.phosphorus  = phosphorus;
        this.zinc = zinc;
        this.vitamin_B6 = vitamin_B6;
        this.folatem = folatem;
        this.selenium = selenium;
        this.sodium_Na = sodium_Na;
        this.vitamin_K = vitamin_K;
        this.magnesium = magnesium;
        this.pantothenic_acid = pantothenic_acid;
        this.niacin = niacin;
        this.thiamin = thiamin;
        this.copper =copper;
        this.choline = choline;
        this.vitamin_B12 = vitamin_B12;
        this.riboflavin = riboflavin;
        this.manganese = manganese;
        this.vitamin_A = vitamin_A;
        this.food_rate_count = food_rate_count;
        this.flouride = flouride;
        this.iron_Fe = iron_Fe;
        this.vitamin_C = vitamin_C;
        this.vitamin_D = vitamin_D;
        this.calcium = calcium;
        this.vitamin_E = vitamin_E;
        this.tag_list = tag_list;

    }

    public GetFoodProfileResult() {

    }

    public Double getServing_weight_grams() {
        return serving_weight_grams;
    }

    public void setServing_weight_grams(Double a) {
        this.serving_weight_grams = a;
    }

    public Double getProtein_value() {
        return protein_value;
    }

    public void setProtein_value(Double a) {
        this.protein_value = a;
    }

    public Double getCalorie_value() {
        return calorie_value;
    }

    public void setCalorie_value(Double a) {
        this.calorie_value = a;
    }

    public Double getFiber_value() {
        return fiber_value;
    }

    public void setFiber_value(Double a) {
        this.fiber_value = a;
    }

    public int getFood_owner_id() {
        return food_owner_id;
    }

    public void setFood_owner_id(int a) {
        this.food_owner_id = a;
    }

    public String getFood_recipe() {
        return food_recipe;
    }

    public void setFood_recipe(String a) {
        this.food_recipe = a;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String a) {
        this.food_description = a;
    }

    public String getFood_image() {
        return food_image;
    }

    public void setFood_image(String a) {
        this.food_image = a;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String a) {
        this.food_name = a;
    }

    public Double getFat_value() {
        return fat_value;
    }

    public void setFat_value(Double a) {
        this.fat_value = a;
    }

    public Double getSugar_value() {
        return sugar_value;
    }

    public void setSugar_value(Double a) {
        this.sugar_value = a;
    }

    public Double getFood_rate() {
        return food_rate;
    }

    public void setFood_rate(Double a) {
        this.food_rate = a;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int a) {
        this.food_id = a;
    }

    public Double getCarbonhydrate_value() {
        return carbohydrate_value;
    }

    public void setCarbohydrate_value(Double a) {
        this.carbohydrate_value = a;
    }

    public Tag[] getTag_list() {
        return tag_list;
    }

    public void setTag_list(Tag[] a) {
        this.tag_list = a;
    }

    public Double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Double a) {
        this.phosphorus = a;
    }

    public Double getZinc() {
        return zinc;
    }

    public void setZinc(Double a) {
        this.zinc = a;
    }

    public Double getVitamin_B6() {
        return vitamin_B6;
    }

    public void setVitamin_B6(Double a) {
        this.vitamin_B6 = a;
    }

    public Double getFolatem() {
        return folatem;
    }

    public void setFolatem(Double a) {
        this.folatem = a;
    }

    public Double getSelenium() {
        return selenium;
    }

    public void setSelenium(Double a) {
        this.selenium = a;
    }

    public Double getSodium_Na() {
        return sodium_Na;
    }

    public void setSodium_Na(Double a) {
        this.sodium_Na = a;
    }

    public Double getvitamin_K() {
        return vitamin_K;
    }

    public void setVitamin_K(Double a) {
        this.vitamin_K = a;
    }

    public Double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Double a) {
        this.magnesium = a;
    }

    public Double getPantothenic_acid() {
        return pantothenic_acid;
    }

    public void setPantothenic_acid(Double a) {
        this.pantothenic_acid = a;
    }

    public Double getNiacin() {
        return niacin;
    }

    public void setNiacin(Double a) {
        this.niacin = a;
    }

    public Double getThiamin() {
        return thiamin;
    }

    public void setThiamin(Double a) {
        this.thiamin = a;
    }

    public Double getCopper() {
        return copper;
    }

    public void setCopper(Double a) {
        this.copper = a;
    }

    public Double getCholine() {
        return choline;
    }

    public void setCholine(Double a) {
        this.choline = a;
    }

    public Double getVitamin_B12() {
        return vitamin_B12;
    }

    public void setVitamin_B12(Double a) {
        this.vitamin_B12 = a;
    }

    public Double getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(Double a) {
        this.riboflavin = a;
    }

    public Double getManganese() {
        return manganese;
    }

    public void setManganese(Double a) {
        this.manganese = a;
    }

    public Double getVitamin_A() {
        return vitamin_A;
    }

    public void setVitamin_A(Double a) {
        this.vitamin_A = a;
    }

    public int getFood_rate_count() {
        return food_rate_count;
    }

    public void setFood_rate_count(int a) {
        this.food_rate_count = a;
    }

    public Double getFlouride() {
        return flouride;
    }

    public void setFlouride(Double a) {
        this.flouride = a;
    }

    public Double getIron_Fe() {
        return iron_Fe;
    }

    public void setIron_Fe(Double a) {
        this.iron_Fe = a;
    }

    public Double getVitamin_C() {
        return vitamin_C;
    }

    public void setVitamin_C(Double a) {
        this.vitamin_C = a;
    }

    public Double getVitamin_D() {
        return vitamin_D;
    }

    public void setVitamin_D(Double a) {
        this.vitamin_D = a;
    }

    public Double getVitamin_E() {
        return vitamin_E;
    }

    public void setVitamin_E(Double a) {
        this.vitamin_E = a;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double a) {
        this.calcium = a;
    }

    public Boolean getEaten() {
        return eaten;
    }

    public void setEaten(Boolean a) {
        this.eaten = a;
    }


}


