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
import com.knowwhatwoueat.kwue.DataModels.GetFoodProfileResult;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import org.w3c.dom.Text;

public class FoodProfile extends AppCompatActivity {

    private RequestQueue queue;
    String url = Constants.endPoint;

    private int searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int foodId = getIntent().getIntExtra("isFood",0);
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
                        Log.d("response", "onResponse: in");
                        //assignSearchTitles(response);
                        //showAlertDialog();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", "That didn't work!");
            }
        });
    }

}
