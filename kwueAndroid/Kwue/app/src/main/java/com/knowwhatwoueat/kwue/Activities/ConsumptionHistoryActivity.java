package com.knowwhatwoueat.kwue.Activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.DataModels.ConsumptionItem;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.SemanticTag;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionHistoryActivity extends AppCompatActivity {
    private List<Food> consumptionHistory;
    private String[] consumptionList;
    private String[] imageList;
    private Toolbar toolbar;


    private String url = Constants.endPoint;

    private RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        queue = Volley.newRequestQueue(this);
        sendConsumptionRequest();
        //// TODO: 26.10.2016 Remove these hardcodes, pull it from backend
        //addDummyFood();
        consumptionList = getFoodNames();
        imageList = getImageUrls();
        //ListAdapter adapter = new ConsumptionListAdapter(this,consumptionList,consumptionList,);
        ListAdapter adapter = new ConsumptionListAdapter(this,consumptionHistory);
        ListView listView = (ListView) findViewById(R.id.consumptionlist);

        listView.setAdapter(adapter);
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
    protected void sendConsumptionRequest(){
        String semanticUrl = url +"get_consumption_history?user_id=" + Constants.user_id + "&setting=" + Constants.consumptionHistorySetting;
        // Request a string response from the provided URL.
        GsonRequest<ConsumptionItem> gsonRequest = new GsonRequest<>(semanticUrl,ConsumptionItem.class, Request.Method.GET,
                new Response.Listener<ConsumptionItem>() {
                    @Override
                    public void onResponse(ConsumptionItem response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: in");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response","That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(gsonRequest);
    }


    private String[] getFoodNames(){
        String[] foods = new String[consumptionHistory.size()];
        for(int i = 0 ; i< consumptionHistory.size();i++){
            //foods[i] = consumptionHistory.get(i).getName();
        }
        return foods;
    }

    private String[] getImageUrls(){
        String[] urls = new String[consumptionHistory.size()];

        for(int i = 0 ; i< consumptionHistory.size();i++){
           // urls[i] = consumptionHistory.get(i).getImageUrl();
        }
        return urls;
    }


}
