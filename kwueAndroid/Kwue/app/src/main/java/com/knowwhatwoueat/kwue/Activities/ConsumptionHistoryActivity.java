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
import com.android.volley.toolbox.StringRequest;
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
    private ArrayList<String> simpleNutritions;
    private ArrayList<String> wholeNutritions;
    private ConsumptionItem consumptionItem;


    private Toolbar toolbar;
    private ListView simpleNutritionList;
    private ListView wholeNutritionList;
    private ListView consumptionListView;

    private ListAdapter simpleNutiritonAdapter;
    private ConsumptionListAdapter consumptionListAdapter;


    private String url = Constants.endPoint;

    private RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);

        queue = Volley.newRequestQueue(this);
        sendConsumptionRequest();

        simpleNutritions = new ArrayList<>();
        wholeNutritions = new ArrayList<>();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        simpleNutritionList = (ListView) findViewById(R.id.simpleNutritionList);
        consumptionListView = (ListView) findViewById(R.id.consumptionlist);


        simpleNutiritonAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,simpleNutritions);

        simpleNutritionList.setAdapter(simpleNutiritonAdapter);


        /*ListAdapter adapter = new ConsumptionListAdapter(this,consumptionHistory);
        ListView listView = (ListView) findViewById(R.id.consumptionlist);

        listView.setAdapter(adapter);*/
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

        GsonRequest<ConsumptionItem> gsonRequest = new GsonRequest<>(semanticUrl,ConsumptionItem.class, Request.Method.GET,
                new Response.Listener<ConsumptionItem>() {
                    @Override
                    public void onResponse(ConsumptionItem response) {
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


    protected void assignConsuptionItem(ConsumptionItem response){
        consumptionItem = response;
        simpleNutritions.add("Fat:" + response.getNutritional_values_dict().getFat_value() + " gram");
        simpleNutritions.add("Carbonhydrate:" + response.getNutritional_values_dict().getCarbohydrate_value() + " gram");
        simpleNutritions.add("Protein:" + response.getNutritional_values_dict().getProtein_value() + " gram");
        simpleNutritions.add("Calorie:" + response.getNutritional_values_dict().getCalorie_value() + " kcal");
        wholeNutritions.add("Calorie:" + response.getNutritional_values_dict().getCalorie_value() + " kcal");
        wholeNutritions.add("Vitamid D:" + response.getNutritional_values_dict().getVitamin_D() + " IU");
        wholeNutritions.add("Zinc:" + response.getNutritional_values_dict().getZinc() + " mg");
        wholeNutritions.add("Vitamin E:" + response.getNutritional_values_dict().getVitamin_E() + " mg");
        wholeNutritions.add("Magnesium:" + response.getNutritional_values_dict().getMagnesium() + " mg");
        wholeNutritions.add("Protein:" + response.getNutritional_values_dict().getProtein_value() + " mg");
        wholeNutritions.add("Folate:" + response.getNutritional_values_dict().getFolatem() + " mcg");
        wholeNutritions.add("Calcium:" + response.getNutritional_values_dict().getCalcium() + " mg");
        wholeNutritions.add("Manganese:" + response.getNutritional_values_dict().getManganese() + " mg");
        wholeNutritions.add("Vitamin A:" + response.getNutritional_values_dict().getVitamin_A() + " IU");
        wholeNutritions.add("Niacin:" + response.getNutritional_values_dict().getNiacin() + " mg");
        wholeNutritions.add("Fiber Value:" + response.getNutritional_values_dict().getFiber_value()+ " gram");
        wholeNutritions.add("Copper:" + response.getNutritional_values_dict().getCopper()+ " mg");
        wholeNutritions.add("Thiamin:" + response.getNutritional_values_dict().getThiamin()+ " mg");
        wholeNutritions.add("Iron Fe:" + response.getNutritional_values_dict().getIron_Fe()+ " mg");
        wholeNutritions.add("Vitamin B6:" + response.getNutritional_values_dict().getVitamin_B6()+ " mg");
        wholeNutritions.add("Vitamin C:" + response.getNutritional_values_dict().getVitamin_C()+ " mg");
        wholeNutritions.add("Phosphorus:" + response.getNutritional_values_dict().getPhosphorus()+ " mg");
        wholeNutritions.add("Flouride:" + response.getNutritional_values_dict().getFlouride()+ " mg");
        wholeNutritions.add("Vitamin B12:" + response.getNutritional_values_dict().getVitamin_B12()+ " mcg");//
        wholeNutritions.add("Pantothenic Acid:" + response.getNutritional_values_dict().getPantothenic_acid()+ " mg");
        wholeNutritions.add("Riboflavin:" + response.getNutritional_values_dict().getRiboflavin()+ " mg");
        wholeNutritions.add("Sugar :" + response.getNutritional_values_dict().getSugar_value()+ " gram");
        wholeNutritions.add("Carbonhydrate:" + response.getNutritional_values_dict().getCarbohydrate_value()+ " gram");
        wholeNutritions.add("Selenium:" + response.getNutritional_values_dict().getSelenium()+ " mcg");
        wholeNutritions.add("Sodium:" + response.getNutritional_values_dict().getSodium_Na()+ " mg");
        wholeNutritions.add("Choline:" + response.getNutritional_values_dict().getCholine()+ " mg");
        wholeNutritions.add("Fat :" + response.getNutritional_values_dict().getFat_value()+ " gram");
        wholeNutritions.add("Serving Weight:" + response.getNutritional_values_dict().getServing_weight_grams()+ " gram");
        wholeNutritions.add("Vitamin K:" + response.getNutritional_values_dict().getVitamin_K()+ " mcg");
        Log.d("print", simpleNutritions.toString());
        Log.d("print whole",wholeNutritions.toString());
    }


}
