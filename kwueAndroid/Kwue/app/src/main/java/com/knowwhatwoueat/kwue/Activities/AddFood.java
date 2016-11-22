package com.knowwhatwoueat.kwue.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.knowwhatwoueat.kwue.Adapters.IngredientListAdapter;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.Ingredient;
import com.knowwhatwoueat.kwue.DataModels.SemanticTag;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddFood extends AppCompatActivity{
    private EditText editFoodTextBox;
    private EditText editDescriptionTextBox;
    private EditText imageUrlBox;
    private Button getTagsButton;
    private EditText semanticTextBox;
    private EditText ingredientName;
    private EditText ingredientQty;
    private Button addIngredientButton;
    private ListView semanticListView;
    private ListView ingredientListView;
    private Button calculateCalorieButton;

    //food model
    private Food foodAdded;
    private ArrayList<String> ingredientNames;
    private ArrayList<SemanticTag> semanticTags;
    private ArrayList<String> semanticTagNames;
    private ArrayList<Integer> ingredientGrams;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Long> checkedSemanticTags;

    private ListAdapter semanticListAdapter;
    private ArrayAdapter ingredientListAdapter;
    private String semanticQuery;

    private AlertDialog alertDialog;


    private RequestQueue queue;
    String url = Constants.endPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        semanticTagNames= new ArrayList<String>();
        ingredientNames = new ArrayList<>();
        semanticTags = new ArrayList<>();
        ingredientGrams = new ArrayList<>();
        ingredients = new ArrayList<>();
        checkedSemanticTags = new ArrayList<>();

        foodAdded = new Food();

        semanticListView = (ListView) findViewById(R.id.semantic_list);
        ingredientListView = (ListView) findViewById(R.id.ingredient_list);


        editFoodTextBox = (EditText) findViewById(R.id.add_food_name);
        editDescriptionTextBox = (EditText) findViewById(R.id.food_description);
        imageUrlBox = (EditText) findViewById(R.id.FoodThumbNail);
        getTagsButton= (Button) findViewById(R.id.semantic_button);
        semanticTextBox = (EditText) findViewById(R.id.semantic_tag_text_box);
        ingredientName = (EditText) findViewById(R.id.ingredient_name);
        ingredientQty = (EditText) findViewById(R.id.ingredient_quantity);
        addIngredientButton = (Button) findViewById(R.id.addIngredientButton);
        calculateCalorieButton = (Button) findViewById(R.id.calculate_button);



        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();



        getTagsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setSemanticQuery(semanticTextBox.getText().toString());
                Log.d("tags button", "onClick: clicked" );
                sendSemanticHttpRequest(semanticQuery);

            }

        });

        addIngredientButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                addIngredient();
                Log.d("add", "onClick: clicked");
                if(ingredients.size() > 5){
                    View item = ingredientListAdapter.getView(0, null, ingredientListView);
                    item.measure(0, 0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, (int) (5.5 * item.getMeasuredHeight()));
                    ingredientListView.setLayoutParams(params);
                }
            }
        });

        ingredientListAdapter = new IngredientListAdapter(this,ingredients);
        ingredientListView.setAdapter(ingredientListAdapter);
        ingredientListView.setVerticalScrollBarEnabled(true);


        semanticListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,semanticTagNames);

        final AlertDialog.Builder build = new AlertDialog.Builder(AddFood.this);
        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = build.create();
        LayoutInflater inflater = getLayoutInflater();
        View convertView =  inflater.inflate(R.layout.semantic_list_dialog, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("List");
        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        lv.setAdapter(semanticListAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // change the checkbox state
                CheckedTextView checkedTextView = ((CheckedTextView)view);
                checkedTextView.setChecked(!checkedTextView.isChecked());
                if(checkedTextView.isChecked())
                    checkedSemanticTags.add(id);
                else
                    checkedSemanticTags.remove(id);
            }
        });

        calculateCalorieButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        requestCalorie();
                    }
                }

        );


        //semanticListView.setAdapter(semanticListAdapter);

        /*semanticListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // change the checkbox state
                CheckedTextView checkedTextView = ((CheckedTextView)view);
                checkedTextView.setChecked(!checkedTextView.isChecked());
                checkedSemanticTags.add(position,checkedTextView.isChecked());
            }
        });*/


        FloatingActionButton sendFoodButton = (FloatingActionButton) findViewById(R.id.fab);
        sendFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAddFoodRequest();
            }

        });



    }
    protected void addIngredient(){
        String str = ingredientName.getText().toString();
        int qty = 0;
        if(ingredientQty.getText().length()>1)
             qty= Integer.valueOf(ingredientQty.getText().toString());
        ingredientNames.add(str);
        ingredientGrams.add(qty);
        ingredientListAdapter.add(new Ingredient(str,qty));
        ingredientName.setText("");
        ingredientQty.setText("");
    }
    protected ListAdapter getIngredientListAdapter(){
        return ingredientListAdapter;
    }
    protected void assignTagTitles(SemanticTag[] response){
        for(int i = 0; i < response.length;i++){
            semanticTagNames.add(response[i].tag_description);
            semanticTags.add(response[i]);
        }
    }
    protected void requestCalorie(){
        String calculateColieURL = url + "get_nutritional_values";
        final JsonArray ingredientArray = new JsonArray();
        for(int i = 0 ; i < ingredients.size();i++){
            JsonObject obj = new JsonObject();
            obj.addProperty("ingredient",ingredients.get(i).getName());
            obj.addProperty("value",""+ ingredients.get(i).getQuantity()+" gr");
            ingredientArray.add(obj);
            StringRequest sr = new StringRequest(Request.Method.POST,calculateColieURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("ingredients", ingredientArray.toString());
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
    }
    protected void sendAddFoodRequest(){
        String addFoodUrl = url + "add_food";
        final JsonArray ingredientArray = new JsonArray();
        final String semanticsResponse;
        Gson gson = new Gson();
        for(int i = 0 ; i < ingredients.size();i++){
            JsonObject obj = new JsonObject();
            obj.addProperty("ingredient",ingredients.get(i).getName());
            obj.addProperty("value",""+ ingredients.get(i).getQuantity()+" gr");
            ingredientArray.add(obj);
        }

        semanticsResponse =gson.toJson(semanticTags.toArray(),SemanticTag[].class);
        StringRequest sr = new StringRequest(Request.Method.POST,addFoodUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("food_description",editDescriptionTextBox.getText().toString());
                params.put("food_name",editFoodTextBox.getText().toString());
                params.put("food_image",imageUrlBox.getText().toString() );
                params.put("food_owner","1");
                params.put("ingredients", ingredientArray.toString());
                params.put("food_tags",semanticsResponse);


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

    protected void setSemanticQuery(String query){
        semanticQuery = query;
    }



    protected void sendSemanticHttpRequest(String query){

        String semanticUrl = url +"search_semantic_tags?tag_name="+ query;
        // Request a string response from the provided URL.
        GsonRequest<SemanticTag[]> gsonRequest = new GsonRequest<>(semanticUrl,SemanticTag[].class, Request.Method.GET,
                new Response.Listener<SemanticTag[]>() {
                    @Override
                    public void onResponse(SemanticTag[] response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: in");
                        assignTagTitles(response);
                        showAlertDialog();
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
    protected void showAlertDialog(){
        alertDialog.show();
    }

}
