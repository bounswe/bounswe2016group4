package com.knowwhatwoueat.kwue.Activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.Adapters.SimpleNutritionAdapter;
import com.knowwhatwoueat.kwue.DataModels.ConsumptionItem;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.MonthlyConsumptionGraph;
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
    private ArrayList<DataPoint> mothlyFat;
    private ArrayList<DataPoint> mothlySugar;
    private ArrayList<DataPoint> mothlyCalorie;
    private ArrayList<DataPoint> mothlyProtein;
    private ArrayList<DataPoint> mothlyCarbohydrate;

    private Toolbar toolbar;
    private ListView simpleNutritionListView;
    private ListView wholeNutritionList;
    private ListView consumptionListView;
    private TextView intervalView;
    private Button showMoreButton;
    private Button showGraphButton;
    private GraphView graphView;

    private SimpleNutritionAdapter simpleNutiritonAdapter;
    private ConsumptionListAdapter consumptionListAdapter;
    private SimpleNutritionAdapter wholeNutritionAdapter;

    private AlertDialog alertDialog;
    private AlertDialog alertDialogGraph;




    private String url = Constants.endPoint;
    private String interval;

    private RequestQueue queue;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);

        //interval = " weekly";
        interval = getIntent().getExtras().getString("consumption_history_interval");

        queue = Volley.newRequestQueue(this);

        consumptionItem = new ConsumptionItem();
        simpleNutritions = new ArrayList<>();
        wholeNutritions = new ArrayList<>();
        consumptionHistory = new ArrayList<>();
        mothlyFat = new ArrayList<>();
        mothlySugar = new ArrayList<>();
        mothlyCalorie = new ArrayList<>();
        mothlyProtein = new ArrayList<>();
        mothlyCarbohydrate = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Consumption History");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        showMoreButton = (Button) findViewById(R.id.showMoreNutritions);
        simpleNutritionListView = (ListView) findViewById(R.id.simpleNutritionList);
        consumptionListView = (ListView) findViewById(R.id.consumptionlist);
        intervalView = (TextView) findViewById(R.id.nutrition_interval);


        simpleNutiritonAdapter = new SimpleNutritionAdapter(this,android.R.layout.simple_list_item_1,simpleNutritions);
        consumptionListAdapter = new ConsumptionListAdapter(this,consumptionHistory);
        wholeNutritionAdapter = new SimpleNutritionAdapter(this,android.R.layout.simple_list_item_1,wholeNutritions);



        simpleNutritionListView.setAdapter(simpleNutiritonAdapter);
        consumptionListView.setAdapter(consumptionListAdapter);

        consumptionListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Food o = (Food) consumptionListView.getItemAtPosition(position);
                Intent i = new Intent(ConsumptionHistoryActivity.this, FoodProfile.class);
                i.putExtra("isFood", o.getFood_id());
                Log.d("choise","" + o.getFood_id());
                startActivity(i);
            }
        });
        intervalView.setText("Your  " + interval+" nutritions:");

        final AlertDialog.Builder build = new AlertDialog.Builder(ConsumptionHistoryActivity.this);
        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = build.create();
        LayoutInflater inflater = getLayoutInflater();
        View convertView =  inflater.inflate(R.layout.nutrition_list_dialog, null);
        wholeNutritionList = (ListView) convertView.findViewById(R.id.list_nutrition);
        wholeNutritionList.setAdapter(wholeNutritionAdapter);
        alertDialog.setView(convertView);
        alertDialog.setTitle("All Nutritions");

        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog for all nutritions
                alertDialog.show();
            }
        });





        final AlertDialog.Builder buildAnother = new AlertDialog.Builder(ConsumptionHistoryActivity.this);
        buildAnother.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialogGraph.dismiss();
            }
        });
        alertDialogGraph = build.create();
        View convertViewGraph =  inflater.inflate(R.layout.graph_dialog, null);

        alertDialogGraph.setView(convertViewGraph);
        alertDialogGraph.setTitle("Monthly Graph");
        graphView = (GraphView) convertViewGraph.findViewById(R.id.graph);


        Spinner dropdownGraphType = (Spinner) this.findViewById(R.id.graph_type);
        String[] items = new String[]{"calorie","sugar","fat","protein","carbohydrate"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownGraphType.setAdapter(typeAdapter);

        dropdownGraphType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean firstTimeShown = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = parent.getItemAtPosition(position).toString();
                //showAlertDialog(type);
                if(!firstTimeShown)
                    showGraph(type);
                else
                    firstTimeShown = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        /*ListAdapter adapter = new ConsumptionListAdapter(this,consumptionHistory);
        ListView listView = (ListView) findViewById(R.id.consumptionlist);

        listView.setAdapter(adapter);*/
    }

    /*protected void showAlertDialog(String type){
        if(!firstTimeDrop){
            alertDialogGraph.show();
        }
        }
    }*/

    protected void showGraph(String type){
        graphView.removeAllSeries();
        LineGraphSeries<DataPoint> series;
        switch (type){
            case "calorie":
                series = new LineGraphSeries<>((DataPoint[]) mothlyCalorie.toArray());
                graphView.addSeries(series);
                return ;
            case "protein":
                series = new LineGraphSeries<>((DataPoint[]) mothlyProtein.toArray());
                graphView.addSeries(series);
                return ;
            case "carbohydrate":
                series = new LineGraphSeries<>((DataPoint[]) mothlyCarbohydrate.toArray());
                graphView.addSeries(series);
                return ;
            case "fat":
                series = new LineGraphSeries<>((DataPoint[]) mothlyFat.toArray());
                graphView.addSeries(series);
                return ;
            case "sugar":
                series = new LineGraphSeries<>((DataPoint[]) mothlySugar.toArray());
                graphView.addSeries(series);
                return ;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        sendConsumptionRequest();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                Log.d("search", "onOptionsItemSelected: ");
                Intent i = new Intent(ConsumptionHistoryActivity.this, BasicSearch.class);
                startActivity(i);
                return true;
            case R.id.advanced_search:
                Intent ik = new Intent(ConsumptionHistoryActivity.this, AdvancedSearch.class);
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
    private void sendConsumptionRequest(){
        String semanticUrl = url +"get_consumption_history?user_id=" + Constants.user_id + "&setting=" + interval;

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
        consumptionListAdapter.addAll(consumptionItem.getFoods());
        MonthlyConsumptionGraph[] graphArray = consumptionItem.getGraph_dict();
        for(int i = 0 ; i < graphArray.length;i++){
            MonthlyConsumptionGraph temp = graphArray[i];
            mothlyCalorie.add(new DataPoint(temp.getDay_number(),temp.getCalorie_value()));
            mothlyCarbohydrate.add(new DataPoint(temp.getDay_number(),temp.getCarbohydrate_value()));
            mothlyFat.add(new DataPoint(temp.getDay_number(),temp.getFat_value()));
            mothlyProtein.add(new DataPoint(temp.getDay_number(),temp.getProtein_value()));
            mothlySugar.add(new DataPoint(temp.getDay_number(),temp.getSugar_value()));
        }

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
        wholeNutritionAdapter.add("Vitamin K:" + consumptionItem.getNutritional_values_dict().getVitamin_K()+ " mcg");
        wholeNutritionAdapter.notifyDataSetChanged();
        Log.d("print", simpleNutritions.toString());
        Log.d("print whole",wholeNutritions.toString());

    }


}
