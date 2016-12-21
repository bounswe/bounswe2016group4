package com.knowwhatwoueat.kwue.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private ListView recommendedListView;
    private List<Food> foods;

    private String url = Constants.endPoint;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Consumption History");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        queue = Volley.newRequestQueue(this);


        foods = new ArrayList<>();

        recommendedListView = (ListView) findViewById(R.id.recommended_list);

        sendHomeRequest();


        ConsumptionListAdapter recommendedListAdapter = new ConsumptionListAdapter(this,foods);
        recommendedListView.setAdapter(recommendedListAdapter);
    }

    protected void sendHomeRequest(){
        int user_id = Constants.getInstance().getUser_id();
        String semanticUrl = url +"get_consumption_history?user_id=" +user_id + "&setting=" ;

        StringRequest gsonRequest = new StringRequest(Request.Method.GET,semanticUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", "onResponse: in" + response);
                        assignRecommendedFoods(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response","That didn't work!" + error);
            }
        });
// Add the request to the RequestQueue.
        queue.add(gsonRequest);
    }

    protected void assignRecommendedFoods(String recommendedResponse){

    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                Log.d("search", "onOptionsItemSelected: ");
                Intent i = new Intent(HomeActivity.this, BasicSearch.class);
                startActivity(i);
                return true;
            case R.id.advanced_search:
                Intent ik = new Intent(HomeActivity.this, AdvancedSearch.class);
                startActivity(ik);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }


        return super.onCreateOptionsMenu(menu);
    }

}
