package com.knowwhatwoueat.kwue.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.DataModels.BasicSearchResult;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.FoodBasicSearch;
import com.knowwhatwoueat.kwue.DataModels.FoodServerBasicSearch;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

public class BasicSearch extends AppCompatActivity  {
    private String basicSearch;
    private ListView basicSearchListView;
    private boolean isUser;
    private ArrayList<Integer> foodId ;
    private ArrayList<Integer> userId ;



    private String searchQuery;

    private ArrayAdapter searchListAdapter;

    private ArrayList<String> responseNamesList;


    private AlertDialog alertDialog;

    private RequestQueue queue;
    String url = Constants.endPoint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_search);

        final EditText basicSearchTextBox = (EditText) findViewById(R.id.basicSearchTextBox);

        basicSearchListView = (ListView) findViewById(R.id.basicSearchList);

        final Button basicSearchButton = (Button) findViewById(R.id.basicSearchButton);


        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        responseNamesList = new ArrayList<String>();
        foodId = new ArrayList<Integer>();
        userId = new ArrayList<Integer>();



        //searchListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, responseNamesList);
        searchListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, responseNamesList);

        final AlertDialog.Builder build = new AlertDialog.Builder(BasicSearch.this);
        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = build.create();
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.basic_search_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Basic Search Results");
        ListView lv = (ListView) convertView.findViewById(R.id.listView3);
        lv.setAdapter(searchListAdapter);
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(i);
                Intent j;
                if(isUser){
                    if(userId.get(i)!=-1) {
                        j = new Intent(BasicSearch.this, ProfilePageActivity.class);
                        System.out.println(userId.get(i));
                        j.putExtra("isUser", userId.get(i));
                        startActivity(j);
                    }
                }else{
                    if(foodId.get(i)!=-1) {
                        j = new Intent(BasicSearch.this, FoodProfile.class);
                        System.out.println(foodId.get(i));
                        j.putExtra("isFood", foodId.get(i));
                        startActivity(j);
                    }
                }
            }
        });

        basicSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responseNamesList.clear();
                setSearchQuery(basicSearchTextBox.getText().toString());
                Log.d("search button", "onClick: clicked");
                sendBasicSearchHttpRequest(searchQuery);

            }
        });
    }


    protected void setSearchQuery(String query) {

        if(query.contains(" ")){
            query= query.replace(" ","%20");
        }
        searchQuery = query;
    }

    protected void assignSearchTitles(BasicSearchResult response) {

        int foodNumber = response.food_set.length;
        if(foodNumber > 0) {
            isUser = false;
            responseNamesList.add("*** Foods ***");
            foodId.add(-1);
        }

        for (int i = 0; i < foodNumber; i++) {
            FoodBasicSearch find = response.food_set[i];

            foodId.add(find.getFood_id());
            responseNamesList.add(find.getFood_name());

        }

        int semanticFoodNumber= response.semantic_food_set.length;
        if(semanticFoodNumber > 0) {
            isUser = false;
            foodId.add(-1);
            responseNamesList.add("*** Semantic Foods ***");

        }
        for (int i = 0; i < semanticFoodNumber; i++) {
            FoodBasicSearch findSF = response.semantic_food_set[i];
            foodId.add(findSF.getFood_id());
            responseNamesList.add(findSF.getFood_name());
        }


        int foodServerNumber = response.user_set.length;
        if(foodServerNumber > 0) {
            isUser = true;
            userId.add(-1);
            responseNamesList.add("*** Food Servers ***");
        }
        for(int j=0;j<foodServerNumber;j++){
            FoodServerBasicSearch findS = response.user_set[j];
            userId.add(findS.getUser_id());
            responseNamesList.add(findS.getUser_name());
        }

        int semanticFoodServerNumber = response.semantic_user_set.length;
        if(semanticFoodServerNumber > 0){
            isUser = true;
            userId.add(-1);
            responseNamesList.add("*** Semantic Food Servers ***");
        }
        for(int j=0;j<semanticFoodServerNumber;j++){
            FoodServerBasicSearch findSF = response.semantic_user_set[j];
            userId.add(findSF.getUser_id());
            responseNamesList.add(findSF.getUser_name());
        }


    }

    protected void showAlertDialog() {
        alertDialog.show();
    }

    protected void sendBasicSearchHttpRequest(String query) {

        String searchUrl = url + "basic_search?user_id=1&search_text=" + query;
        System.out.println(searchUrl);

        // Request a string response from the provided URL.
        GsonRequest<BasicSearchResult> gsonRequest = new GsonRequest<>(searchUrl, BasicSearchResult.class, Request.Method.GET,
                new Response.Listener<BasicSearchResult>() {
                    @Override
                    public void onResponse(BasicSearchResult response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: in");
                        assignSearchTitles(response);
                        showAlertDialog();
                        /*
                        Intent i = new Intent(BasicSearch.this, SearchResult.class);

                        i.putExtra("FoodSet", food_set);
                        i.putExtra("SemanticFoodSet", semantic_food_set);
                        i.putExtra("UserSet",user_set);
                        i.putExtra("SemanticUserSet",semantic_user_set);

                        startActivity(i);
                        */
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(gsonRequest);
    }


}
