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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;


public class AddFood extends AppCompatActivity{
    private EditText editFoodTextBox;
    private EditText editDescriptionTextBox;
    private EditText imageUrlBox;
    private Button getTagsButton;
    private EditText semanticTextBox;
    private Food foodAdded;
    private ArrayList<String> semanticTags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView mTextView = (TextView) findViewById(R.id.connection_text) ;
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="http://10.0.2.2:8000/search_semantic_tags";
        String url = "http://www.google.com";
        String jsonrequest = "japanese";
        JSONObject requestObj;
        requestObj = new JSONObject();
        try {
            requestObj.put("tag_name",jsonrequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
// Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,requestObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response","That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


        setContentView(R.layout.activity_add_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        semanticTags = new ArrayList<String>();
        foodAdded = new Food();

        semanticTags.add("Japanese");
        semanticTags.add("Sushi");


        editFoodTextBox = (EditText) findViewById(R.id.add_food_name);
        editDescriptionTextBox = (EditText) findViewById(R.id.food_description);
        imageUrlBox = (EditText) findViewById(R.id.FoodThumbNail);
        getTagsButton= (Button) findViewById(R.id.semantic_button);
        semanticTextBox = (EditText) findViewById(R.id.semantic_tag_text_box);


        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,semanticTags);
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


        getTagsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String semanticQuery = semanticTextBox.getText().toString();
                Log.d("tag", "onClick: clicked" + semanticQuery);
            }

        });

        FloatingActionButton sendFoodButton = (FloatingActionButton) findViewById(R.id.fab);
        sendFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodAdded.setImageUrl(imageUrlBox.getText().toString());
                foodAdded.setInfo(editDescriptionTextBox.getText().toString());
                foodAdded.setName(editFoodTextBox.getText().toString());
                foodAdded.setTagList(semanticTags);
            }
        });


    }
}
