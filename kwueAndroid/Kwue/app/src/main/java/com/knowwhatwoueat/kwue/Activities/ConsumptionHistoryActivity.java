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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.Adapters.SimpleNutritionAdapter;
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
    private ArrayList<String> simpleNutritions;
    private ArrayList<String> wholeNutritions;
    private ConsumptionItem consumptionItem;

    private Toolbar toolbar;
    private ListView simpleNutritionListView;
    private ListView wholeNutritionList;
    private ListView consumptionListView;
    private TextView intervalView;
    private Button showMoreButton;

    private SimpleNutritionAdapter simpleNutiritonAdapter;
    private ConsumptionListAdapter consumptionListAdapter;


    private String url = Constants.endPoint;
    private String interval;

    private RequestQueue queue;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);

        interval = " weekly";
        //interval = getIntent().getExtras().getString("consumption_history_interval");

        queue = Volley.newRequestQueue(this);

        consumptionItem = new ConsumptionItem();
        simpleNutritions = new ArrayList<>();
        wholeNutritions = new ArrayList<>();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        showMoreButton = (Button) findViewById(R.id.showMoreNutritions);
        simpleNutritionListView = (ListView) findViewById(R.id.simpleNutritionList);
        consumptionListView = (ListView) findViewById(R.id.consumptionlist);
        intervalView = (TextView) findViewById(R.id.nutrition_interval);

        simpleNutiritonAdapter = new SimpleNutritionAdapter(this,android.R.layout.simple_list_item_1,simpleNutritions);
        consumptionListAdapter = new ConsumptionListAdapter(this,consumptionItem.getFoods());

        simpleNutritionListView.setAdapter(simpleNutiritonAdapter);
        consumptionListView.setAdapter(consumptionListAdapter);

        intervalView.setText("Your" + interval+" nutritions:");

        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show dialog here
            }
        });


        /*ListAdapter adapter = new ConsumptionListAdapter(this,consumptionHistory);
        ListView listView = (ListView) findViewById(R.id.consumptionlist);

        listView.setAdapter(adapter);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        sendConsumptionRequest();
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
    private void sendConsumptionRequest(){
        String semanticUrl = url +"get_consumption_history?user_id=" + Constants.user_id + "&setting=" + Constants.consumptionHistorySetting;

        StringRequest gsonRequest = new StringRequest(Request.Method.GET,semanticUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", "onResponse: in" + response);
                        assignConsuptionItem(response);
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


    private void assignConsuptionItem(String response){
        consumptionItem = gson.fromJson(response,ConsumptionItem.class);
        consumptionListAdapter.notifyDataSetChanged();
        simpleNutiritonAdapter.add("Fat:" + consumptionItem.getNutritional_values_dict().getFat_value() + " gram");
        simpleNutiritonAdapter.add("Carbonhydrate:" + consumptionItem.getNutritional_values_dict().getCarbohydrate_value() + " gram");
        simpleNutiritonAdapter.add("Protein:" + consumptionItem.getNutritional_values_dict().getProtein_value() + " gram");
        simpleNutiritonAdapter.add("Calorie:" + consumptionItem.getNutritional_values_dict().getCalorie_value() + " kcal");
        wholeNutritions.add("Calorie:" + consumptionItem.getNutritional_values_dict().getCalorie_value() + " kcal");
        wholeNutritions.add("Vitamid D:" + consumptionItem.getNutritional_values_dict().getVitamin_D() + " IU");
        wholeNutritions.add("Zinc:" + consumptionItem.getNutritional_values_dict().getZinc() + " mg");
        wholeNutritions.add("Vitamin E:" + consumptionItem.getNutritional_values_dict().getVitamin_E() + " mg");
        wholeNutritions.add("Magnesium:" + consumptionItem.getNutritional_values_dict().getMagnesium() + " mg");
        wholeNutritions.add("Protein:" + consumptionItem.getNutritional_values_dict().getProtein_value() + " mg");
        wholeNutritions.add("Folate:" + consumptionItem.getNutritional_values_dict().getFolatem() + " mcg");
        wholeNutritions.add("Calcium:" + consumptionItem.getNutritional_values_dict().getCalcium() + " mg");
        wholeNutritions.add("Manganese:" + consumptionItem.getNutritional_values_dict().getManganese() + " mg");
        wholeNutritions.add("Vitamin A:" + consumptionItem.getNutritional_values_dict().getVitamin_A() + " IU");
        wholeNutritions.add("Niacin:" + consumptionItem.getNutritional_values_dict().getNiacin() + " mg");
        wholeNutritions.add("Fiber Value:" + consumptionItem.getNutritional_values_dict().getFiber_value()+ " gram");
        wholeNutritions.add("Copper:" + consumptionItem.getNutritional_values_dict().getCopper()+ " mg");
        wholeNutritions.add("Thiamin:" + consumptionItem.getNutritional_values_dict().getThiamin()+ " mg");
        wholeNutritions.add("Iron Fe:" + consumptionItem.getNutritional_values_dict().getIron_Fe()+ " mg");
        wholeNutritions.add("Vitamin B6:" + consumptionItem.getNutritional_values_dict().getVitamin_B6()+ " mg");
        wholeNutritions.add("Vitamin C:" + consumptionItem.getNutritional_values_dict().getVitamin_C()+ " mg");
        wholeNutritions.add("Phosphorus:" + consumptionItem.getNutritional_values_dict().getPhosphorus()+ " mg");
        wholeNutritions.add("Flouride:" + consumptionItem.getNutritional_values_dict().getFlouride()+ " mg");
        wholeNutritions.add("Vitamin B12:" + consumptionItem.getNutritional_values_dict().getVitamin_B12()+ " mcg");//
        wholeNutritions.add("Pantothenic Acid:" + consumptionItem.getNutritional_values_dict().getPantothenic_acid()+ " mg");
        wholeNutritions.add("Riboflavin:" + consumptionItem.getNutritional_values_dict().getRiboflavin()+ " mg");
        wholeNutritions.add("Sugar :" + consumptionItem.getNutritional_values_dict().getSugar_value()+ " gram");
        wholeNutritions.add("Carbonhydrate:" + consumptionItem.getNutritional_values_dict().getCarbohydrate_value()+ " gram");
        wholeNutritions.add("Selenium:" + consumptionItem.getNutritional_values_dict().getSelenium()+ " mcg");
        wholeNutritions.add("Sodium:" + consumptionItem.getNutritional_values_dict().getSodium_Na()+ " mg");
        wholeNutritions.add("Choline:" + consumptionItem.getNutritional_values_dict().getCholine()+ " mg");
        wholeNutritions.add("Fat :" + consumptionItem.getNutritional_values_dict().getFat_value()+ " gram");
        wholeNutritions.add("Serving Weight:" + consumptionItem.getNutritional_values_dict().getServing_weight_grams()+ " gram");
        wholeNutritions.add("Vitamin K:" + consumptionItem.getNutritional_values_dict().getVitamin_K()+ " mcg");
        Log.d("print", simpleNutritions.toString());
        Log.d("print whole",wholeNutritions.toString());

    }


}
