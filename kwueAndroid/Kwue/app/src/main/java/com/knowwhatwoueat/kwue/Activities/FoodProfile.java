package com.knowwhatwoueat.kwue.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

    private RequestQueue queue;
    String url = Constants.endPoint;

    private int searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        int foodId = getIntent().getIntExtra("isFood", 0);
        setSearchQuery(foodId);
        sendFoodProfileHttpRequest(searchQuery);


        //TextView foodIdTextView = (TextView) findViewById(R.id.foodId);
        //foodIdTextView.setText(foodId+" ");
        //System.out.print(foodId);


        //j.putExtra("isFood",foodId.get(i));
        // Instantiate the RequestQueue.

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
                        assignFoodValues(response);
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
        phosphorus = response.getPhosphorus();
        zinc = response.getZinc();
        vitamin_B6 = response.getVitamin_B6();
        folatem = response.getFolatem();
        selenium = response.getSelenium();
        sodium_Na = response.getSodium_Na();
        vitamin_K = response.getvitamin_K();
        magnesium = response.getMagnesium();
        pantothenic_acid = response.getPantothenic_acid();
        niacin = response.getNiacin();
        thiamin = response.getThiamin();
        copper = response.getCopper();
        choline = response.getCholine();
        vitamin_B12 = response.getVitamin_B12();
        riboflavin = response.getRiboflavin();
        manganese = response.getManganese();
        vitamin_A = response.getVitamin_A();
        food_rate_count = response.getFood_rate_count();
        flouride = response.getFlouride();
        iron_Fe = response.getIron_Fe();
        vitamin_C = response.getVitamin_C();
        vitamin_D = response.getVitamin_D();
        calcium = response.getCalcium();
        vitamin_E = response.getVitamin_E();
        System.out.println(food_name+" 9");
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
        assingTextView();

    }

    protected void assingTextView() {

        TextView foodNameTextView = (TextView) findViewById(R.id.foodName);
        foodNameTextView.setText(food_name);

        final Button markAsEaten = (Button) findViewById(R.id.markAsEaten);
        markAsEaten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("eaten button", "onClick: clicked");
                sendMarkAsEatenHttpRequest(food_id);

            }
        });

        new FoodProfile.DownloadImageTask((ImageView) findViewById(R.id.foodImage))
                .execute(food_image);

        TextView foodDescriptionTextView = (TextView) findViewById(R.id.foodDescription);
        foodDescriptionTextView.setText(food_description);

        TextView foodRecipeTextView = (TextView) findViewById(R.id.foodRecipe);
        foodRecipeTextView.setText(food_recipe);

        TextView vitaminATextView = (TextView) findViewById(R.id.vitaminA);
        vitaminATextView.setText(vitamin_A+"");

        TextView vitaminB6TextView = (TextView) findViewById(R.id.vitaminB6);
        vitaminB6TextView.setText(vitamin_B6+"");

        TextView vitaminB12TextView = (TextView) findViewById(R.id.vitaminB12);
        vitaminB12TextView.setText(vitamin_B12+"");

        TextView vitaminCTextView = (TextView) findViewById(R.id.vitaminC);
        vitaminCTextView.setText(vitamin_C+"");

        TextView vitaminDTextView = (TextView) findViewById(R.id.vitaminD);
        vitaminDTextView.setText(vitamin_D+"");

        TextView vitaminETextView = (TextView) findViewById(R.id.vitaminE);
        vitaminETextView.setText(vitamin_E+"");

        TextView vitaminKTextView = (TextView) findViewById(R.id.vitaminK);
        vitaminKTextView.setText(vitamin_K+"");

        TextView carbonhydrateTextView = (TextView) findViewById(R.id.carbonhydrateValue);
        carbonhydrateTextView.setText(carbohydrate_value+"");

        TextView proteinTextView = (TextView) findViewById(R.id.proteinValue);
        proteinTextView.setText(protein_value+"");

        TextView sugarTextView = (TextView) findViewById(R.id.sugarValue);
        sugarTextView.setText(sugar_value+"");

        TextView fatTextView = (TextView) findViewById(R.id.fatValue);
        fatTextView.setText(fat_value+"");

        TextView calorieTextView = (TextView) findViewById(R.id.calorieValue);
        calorieTextView.setText(calorie_value+"");

        TextView fiberTextView = (TextView) findViewById(R.id.fiberValue);
        fiberTextView.setText(fiber_value+"");

        int length = tag_list.length;

        String description = "";
        for(int i=0;i<length;i++){
            Tag o = tag_list[i];
            description = description+ o.getTag_description()+"\n";
        }

        TextView tagDescriptionTextView = (TextView) findViewById(R.id.tagDescription);
        tagDescriptionTextView.setText(description);


        TextView phosTextView = (TextView) findViewById(R.id.phosphorus);
        phosTextView.setText(phosphorus+"");

        TextView folTextView = (TextView) findViewById(R.id.folatem);
        folTextView.setText(folatem+"");

        TextView selTextView = (TextView) findViewById(R.id.selenium);
        selTextView.setText(selenium+"");

        TextView sodTextView = (TextView) findViewById(R.id.sodium);
        sodTextView.setText(sodium_Na+"");

        TextView magTextView = (TextView) findViewById(R.id.magnesium);
        magTextView.setText(magnesium+"");

        TextView thiTextView = (TextView) findViewById(R.id.thiomin);
        thiTextView.setText(thiamin+"");

        TextView pantTextView = (TextView) findViewById(R.id.pantothenic_acid);
        pantTextView.setText(pantothenic_acid+"");

        TextView niTextView = (TextView) findViewById(R.id.niacin);
        niTextView.setText(niacin+"");

        TextView copTextView = (TextView) findViewById(R.id.cooper);
        copTextView.setText(copper+"");

        TextView choTextView = (TextView) findViewById(R.id.choline);
        choTextView.setText(choline+"");

        TextView riTextView = (TextView) findViewById(R.id.riboflovin);
        riTextView.setText(riboflavin+"");

        TextView irTextView = (TextView) findViewById(R.id.iron);
        irTextView.setText(iron_Fe+"");

        TextView calTextView = (TextView) findViewById(R.id.calcium);
        calTextView.setText(calcium+"");

        TextView flTextView = (TextView) findViewById(R.id.flouride);
        flTextView.setText(flouride+"");

        TextView manTextView = (TextView) findViewById(R.id.manganese);
        manTextView.setText(manganese+"");



    }

    protected void sendMarkAsEatenHttpRequest(int query) {

        String markAsEatenUrl = url + "mark_as_eaten";
        final String semanticsResponse;
        Gson gson = new Gson();
        StringRequest sr = new StringRequest(Request.Method.POST,markAsEatenUrl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("mark as eaten", "onResponse: added" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id","1");
                params.put("food_id",""+food_id);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



}


