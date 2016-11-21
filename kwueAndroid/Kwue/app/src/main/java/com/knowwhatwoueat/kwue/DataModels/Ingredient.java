package com.knowwhatwoueat.kwue.DataModels;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 19.11.2016.
 */

public class Ingredient {
    private String name;
    private int quantity;

    public Ingredient(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
