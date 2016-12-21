package com.knowwhatwoueat.kwue.DataModels;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 25.10.2016.
 *
 */

public class User {
    public String user_name;
    public String user_nick;
    public String user_email_address;
    public String user_image;
    public boolean user_type;
    public Tag[] tag_list;
    public String[] unwanted_list;
    public String[] wanted_list;
    public double protein_lower_bound;
    public double fat_lower_bound;
    public double carbohydrate_lower_bound;
    public double calorie_lower_bound;
    public double sugar_lower_bound;
    public double protein_upper_bound;
    public double fat_upper_bound ;
    public double carbohydrate_upper_bound;
    public double calorie_upper_bound;
    public double sugar_upper_bound;
    public String user_password;
    public List<Food> foods;



}
