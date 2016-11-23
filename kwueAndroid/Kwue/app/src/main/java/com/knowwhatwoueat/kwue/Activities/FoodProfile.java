package com.knowwhatwoueat.kwue.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.DataModels.BasicSearchResult;
import com.knowwhatwoueat.kwue.DataModels.FoodProfileModel;
import com.knowwhatwoueat.kwue.DataModels.GetFoodProfileResult;
import com.knowwhatwoueat.kwue.DataModels.Tag;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import org.w3c.dom.Text;

public class FoodProfile extends AppCompatActivity {

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
    private Tag[] tag_list;

    private RequestQueue queue;
    String url = Constants.endPoint;

    private int searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int foodId = getIntent().getIntExtra("isFood", 0);
        setSearchQuery(foodId);
        sendFoodProfileHttpRequest(searchQuery);


        //TextView foodIdTextView = (TextView) findViewById(R.id.foodId);
        //foodIdTextView.setText(foodId+" ");
        //System.out.print(foodId);


        //j.putExtra("isFood",foodId.get(i));
        // Instantiate the RequestQueue.

        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
    }

    protected void setSearchQuery(int query) {
        searchQuery = query;
    }

    protected void sendFoodProfileHttpRequest(int query) {

        String searchUrl = url + "get__food?food_id=" + query;
        System.out.println(searchUrl);

        // Request a string response from the provided URL.
        GsonRequest<GetFoodProfileResult> gsonRequest = new GsonRequest<>(searchUrl, GetFoodProfileResult.class, Request.Method.GET,
                new Response.Listener<GetFoodProfileResult>() {
                    @Override
                    public void onResponse(GetFoodProfileResult response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: getFood");
                        //assignFoodValues(response);
                        //showAlertDialog();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", "That didn't work!");
            }
        });
        queue.add(gsonRequest);
    }

    protected void assignFoodValues(GetFoodProfileResult response) {

        serving_weight_grams = response.getServing_weight_grams();

        protein_value = response.getProtein_value();

        calorie_value = response.getCalorie_value();

        fiber_value = response.getFiber_value();

        food_owner_id = response.getFood_owner_id();

        food_recipe = response.getFood_recipe();

        food_description = response.getFood_description();

        food_image = response.getFood_image();

        food_name = response.getFood_name();

        fat_value = response.getFat_value();

        sugar_value = response.getSugar_value();

        food_rate = response.getFood_rate();

        food_id = response.getFood_id();

        carbohydrate_value = response.getCarbonhydrate_value();

        tag_list = response.getTag_list();

/*
        System.out.println(serving_weight_grams+" 1");
        System.out.println(protein_value+" 2");
        System.out.println(calorie_value+" 3");
        System.out.println(fiber_value+" 4");
        System.out.println(food_owner_id+" 5");
        System.out.println(food_recipe+" 6");
        System.out.println(food_description+" 7");
        System.out.println(food_image+" 8");
        System.out.println(food_name+" 9");
        System.out.println(fat_value+" 10");
        System.out.println(sugar_value+" 11");
        System.out.println(food_rate+" 12");
        System.out.println(food_id+" 13");
        System.out.println(carbohydrate_value+" 14");
*/
    }
}


