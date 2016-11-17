package com.knowwhatwoueat.kwue.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.SemanticTag;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import java.util.ArrayList;


public class AddFood extends AppCompatActivity{
    private EditText editFoodTextBox;
    private EditText editDescriptionTextBox;
    private EditText imageUrlBox;
    private Button getTagsButton;
    private EditText semanticTextBox;
    private Food foodAdded;
    private ArrayList<String> semanticTagNames;
    private String semanticQuery;


    private RequestQueue queue;
    String url ="http://10.0.2.2:8000/search_semantic_tags?tag_name=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        semanticTagNames= new ArrayList<String>();
        foodAdded = new Food();

        editFoodTextBox = (EditText) findViewById(R.id.add_food_name);
        editDescriptionTextBox = (EditText) findViewById(R.id.food_description);
        imageUrlBox = (EditText) findViewById(R.id.FoodThumbNail);
        getTagsButton= (Button) findViewById(R.id.semantic_button);
        semanticTextBox = (EditText) findViewById(R.id.semantic_tag_text_box);


        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();



        getTagsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setSemanticQuery(semanticTextBox.getText().toString());
                Log.d("tag", "onClick: clicked" );
                sendSemanticHttpRequest(semanticQuery);
            }

        });





        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,semanticTagNames);
        ListView semanticListView = (ListView) findViewById(R.id.semantic_list);
        semanticListView.setAdapter(listAdapter);

        semanticListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // change the checkbox state
                CheckedTextView checkedTextView = ((CheckedTextView)view);
                checkedTextView.setChecked(!checkedTextView.isChecked());
            }
        });




        FloatingActionButton sendFoodButton = (FloatingActionButton) findViewById(R.id.fab);
        sendFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FAB", "onClick: button pressed");
                foodAdded.setImageUrl(imageUrlBox.getText().toString());
                foodAdded.setInfo(editDescriptionTextBox.getText().toString());
                foodAdded.setName(editFoodTextBox.getText().toString());
                foodAdded.setTagList(semanticTagNames);
                String url = "http://10.0.2.2:8000/add_food?food_description=" + editDescriptionTextBox.getText().toString() +"&food_name=" + editFoodTextBox.getText().toString()
                        +"&food_image="+imageUrlBox.getText().toString()+"&food_owner=1&food_recipe=100 grams potato";



                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                editFoodTextBox.setText(response);
                                Log.d("response", "onResponse: "+ response.toString());

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("response","That didn't work!");
                    }
                });
                /*JSONArray foodAddRequest = new JSONArray();

                foodAddRequest.put(new JSONObject().put("food_image",imageUrlBox.getText().toString()));
                foodAddRequest.put(new JSONObject().put("food_description",editDescriptionTextBox.getText().toString());
                foodAddRequest.put(new JSONObject().put("food_name",editFoodTextBox.getText().toString());
                foodAddRequest.put(new JSONObject().put("food_tags",semanticTags.toString());
                foodAddRequest.put(new JSONObject().put("food_recipe","recipe");
                foodAddRequest.put(new JSONObject().put("food_owner","owner");
                Log.d("Json:  ", foodAddRequest.toString());*/
            }

        });



    }

    protected void assignTagTitles(SemanticTag[] response){
        for(int i = 0; i < response.length;i++){
            semanticTagNames.add(response[i].itemLabel);
        }
    }


    protected void setSemanticQuery(String query){
        semanticQuery = query;
    }

    protected void sendSemanticHttpRequest(String query){

        String semanticUrl = url + query;
        // Request a string response from the provided URL.
        GsonRequest<SemanticTag[]> gsonRequest = new GsonRequest<>(semanticUrl,SemanticTag[].class,
                new Response.Listener<SemanticTag[]>() {
                    @Override
                    public void onResponse(SemanticTag[] response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: "+ response.toString());
                        assignTagTitles(response);
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
}
