package com.knowwhatwoueat.kwue.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.DataModels.EatingPreferences;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.User;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gökberk Erüst on 22.11.2016
 */
public class ProfilePageActivity extends AppCompatActivity {

    public static User user = new User();
    int userId = 1 ;
    public List<Food> consumptionHistory ;
    private RequestQueue queue;
    String url = Constants.endPoint;

    public static String intervalChoise = "daily" ;
    public List<String> eatingPrefs = new ArrayList<>();
    private AlertDialog alertDialog;

    public static EatingPreferences  eatPref= new EatingPreferences();
    public static View convertView ;
    public static LayoutInflater inflater ;


    public static List<String> wantedList = new ArrayList<>();
    public static List<String> unWantedList = new ArrayList<>();

    public static ListAdapter wantedListAdapter ;
    public static ListView lv_wanted ;

    public static ListAdapter unWantedListAdapter ;
    public static ListView lv_unwanted ;

    public static String wanted_list ;
    public static String unwanted_list;

    public Gson gson ;

    private Button addFoodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        queue = Volley.newRequestQueue(this);

        addFoodButton = (Button) findViewById(R.id.add_food_button);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilePageActivity.this, AddFood.class);
                startActivity(i);
            }
        });

        consumptionHistory = new ArrayList<>();

        requestUser(userId);

        Log.d("print", "deneme: " + user.user_name);
        Log.d("print", "deneme: " + user.user_email_address);




    }


    protected void requestUser(int userId){
        String userUrl = url + "get_user?user_id=" + userId;

        GsonRequest<User> gsonRequest = new GsonRequest<>(userUrl,User.class, Request.Method.GET,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: in" + response);
                        if(!response.user_type) {
                            setViewNormalUser(response);
                        }else{
                            setViewFoodServer(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response","That didn't work!" + error);
            }
        });
        queue.add(gsonRequest);
    }










    protected void setViewNormalUser(User user) {

        new DownloadImageTask((ImageView) findViewById(R.id.user_profile_image))
                .execute(user.user_image);

        TextView userName =(TextView) this.findViewById(R.id.user_name);
        userName.setText(user.user_name);

        TextView userMailAddress =(TextView) this.findViewById(R.id.user_email_address);
        userMailAddress.setText(user.user_email_address);


        ListView lw = (ListView) this.findViewById(R.id.consumption_history);
        ListAdapter listAdapter = new ConsumptionListAdapter(this,consumptionHistory);
        lw.setAdapter(listAdapter);

        ListView lw2 = (ListView) this.findViewById(R.id.eating_preferences);
        eatingPrefs = setEatPref(user);
        if(eatingPrefs != null) {
            ArrayAdapter<String> eatingPrefAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eatingPrefs);
            lw2.setAdapter(eatingPrefAdapter);
        }

        Spinner dropdownConsumptionInterval = (Spinner) this.findViewById(R.id.consumption_history_interval);
        String[] items = new String[]{"daily","weekly","monthly","alltime"};
        ArrayAdapter<String> intervalAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        intervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownConsumptionInterval.setAdapter(intervalAdapter);
        dropdownConsumptionInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intervalChoise = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button consumptionButton = (Button) findViewById(R.id.all_consumption_history);
        consumptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfilePageActivity.this, ConsumptionHistoryActivity.class);
                i.putExtra("consumption_history_interval", intervalChoise);
                Log.d("choise",intervalChoise);
                startActivity(i);
            }
        });


        final AlertDialog.Builder build = new AlertDialog.Builder(ProfilePageActivity.this);
        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                updateEatingPrefs();
                alertDialog.dismiss();
            }
        });
        alertDialog = build.create();
        inflater = getLayoutInflater();
        convertView = inflater.inflate(R.layout.update_eating_preferences, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Eating Preferences");

        setHint(user);

        Button eatingPrefUpdate = (Button) findViewById(R.id.update_eating_preferences);
        eatingPrefUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

                Button wantedList = (Button) convertView.findViewById(R.id.add_wanted_item);
                wantedList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addWantedItem();


                    }
                });

                Button unwantedList = (Button) convertView.findViewById(R.id.add_unwanted_item);
                unwantedList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addUnwantedItem();

                    }
                });


            }
        });





    }

    public void setHint(User user){
        EditText tv = (EditText) convertView.findViewById(R.id.update_protein_lower_bound);
        tv.setHint(""+user.protein_lower_bound);

        EditText tv1 = (EditText) convertView.findViewById(R.id.update_fat_lower_bound);
        tv1.setHint(""+user.fat_lower_bound);

        EditText tv2 = (EditText) convertView.findViewById(R.id.update_carbohydrate_lower_bound);
        tv2.setHint(""+user.carbohydrate_lower_bound);

        EditText tv3 = (EditText) convertView.findViewById(R.id.update_calorie_lower_bound);
        tv3.setHint(""+user.calorie_lower_bound);

        EditText tv4 = (EditText) convertView.findViewById(R.id.update_sugar_lower_bound);
        tv4.setHint(""+user.sugar_lower_bound);

        EditText tv5 = (EditText) convertView.findViewById(R.id.update_protein_upper_bound);
        tv5.setHint(""+user.protein_upper_bound);

        EditText tv6 = (EditText) convertView.findViewById(R.id.update_fat_upper_bound);
        tv6.setHint(""+user.fat_upper_bound);

        EditText tv7 = (EditText) convertView.findViewById(R.id.update_carbohydrate_upper_bound);
        tv7.setHint(""+user.carbohydrate_upper_bound);

        EditText tv8 = (EditText) convertView.findViewById(R.id.update_calorie_upper_bound);
        tv8.setHint(""+user.calorie_upper_bound);

        EditText tv9= (EditText) convertView.findViewById(R.id.update_sugar_upper_bound);
        tv9.setHint(""+user.sugar_upper_bound);
    }
    public void updateEatingPrefs(){
        EditText tv = (EditText) convertView.findViewById(R.id.update_protein_lower_bound);
         eatPref.protein_lower_bound = Double.parseDouble(tv.getText().toString());

        EditText tv1 = (EditText) convertView.findViewById(R.id.update_fat_lower_bound);
        eatPref.fat_lower_bound = Double.parseDouble(tv1.getText().toString());

        EditText tv2 = (EditText) convertView.findViewById(R.id.update_carbohydrate_lower_bound);
        eatPref.carbohydrate_lower_bound = Double.parseDouble(tv2.getText().toString());

        EditText tv3 = (EditText) convertView.findViewById(R.id.update_calorie_lower_bound);
        eatPref.calorie_lower_bound = Double.parseDouble(tv3.getText().toString());

        EditText tv4 = (EditText) convertView.findViewById(R.id.update_sugar_lower_bound);
        eatPref.sugar_lower_bound = Double.parseDouble(tv4.getText().toString());

        EditText tv5 = (EditText) convertView.findViewById(R.id.update_protein_upper_bound);
        eatPref.protein_upper_bound = Double.parseDouble(tv5.getText().toString());

        EditText tv6 = (EditText) convertView.findViewById(R.id.update_fat_upper_bound);
        eatPref.fat_upper_bound = Double.parseDouble(tv6.getText().toString());

        EditText tv7 = (EditText) convertView.findViewById(R.id.update_carbohydrate_upper_bound);
        eatPref.carbonhydrate_upper_bound = Double.parseDouble(tv7.getText().toString());

        EditText tv8 = (EditText) convertView.findViewById(R.id.update_calorie_upper_bound);
        eatPref.calorie_upper_bound = Double.parseDouble(tv8.getText().toString());

        EditText tv9= (EditText) convertView.findViewById(R.id.update_sugar_upper_bound);
        eatPref.sugar_upper_bound = Double.parseDouble(tv9.getText().toString());

        sendUpdateEatingPreferences();

       }

    public void addWantedItem(){
        EditText tw = (EditText) convertView.findViewById(R.id.update_wanted_item);
        wantedList.add(tw.getText().toString());

        lv_wanted = (ListView) convertView.findViewById(R.id.wanted_list);
        wantedListAdapter = new ArrayAdapter<String>(convertView.getContext(), android.R.layout.simple_list_item_1,wantedList);
        lv_wanted.setAdapter(wantedListAdapter);



    }
    public void addUnwantedItem(){
        EditText tw = (EditText) convertView.findViewById(R.id.update_unwanted_item);
        unWantedList.add(tw.getText().toString());

        lv_unwanted = (ListView) convertView.findViewById(R.id.unwanted_list);
        unWantedListAdapter = new ArrayAdapter<String>(convertView.getContext(), android.R.layout.simple_list_item_1,unWantedList);
        lv_unwanted.setAdapter(unWantedListAdapter);


    }

    public void sendUpdateEatingPreferences(){
        String updateEatPref = url + "update_eating_preferences";
        StringRequest sr = new StringRequest(Request.Method.POST,updateEatPref, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("add food", "onResponse: added" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                gson = new Gson();
                wanted_list = gson.toJson(wantedList);
                unwanted_list = gson.toJson(unwanted_list);
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id","1");
                params.put("protein_lower_bound",""+eatPref.protein_lower_bound);
                params.put("fat_lower_bound",""+eatPref.fat_lower_bound);
                params.put("carbohydrate_lower_bound",""+eatPref.carbohydrate_lower_bound);
                params.put("calorie_lower_bound", ""+eatPref.calorie_lower_bound);
                params.put("sugar_lower_bound",""+eatPref.sugar_lower_bound);
                params.put("protein_upper_bound",""+eatPref.protein_upper_bound);
                params.put("fat_upper_bound",""+eatPref.fat_upper_bound);
                params.put("carbohydrate_upper_bound",""+eatPref.carbonhydrate_upper_bound);
                params.put("calorie_upper_bound", ""+eatPref.calorie_upper_bound);
                params.put("sugar_upper_bound",""+eatPref.sugar_upper_bound);
                params.put("wanted_list",wanted_list);
                params.put("unwanted_list",unwanted_list);



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

    public List<String> setEatPref(User user){
        List<String> temp = new ArrayList<>();
        temp.add("Protein Lower Bound  \t \t    : " + user.protein_lower_bound);
        temp.add("Fat Lower Bound       \t \t   : " + user.fat_lower_bound);
        temp.add("Carbohydrate Lower Bound \t : " + user.carbohydrate_lower_bound);
        temp.add("Calorie Lower Bound     \t : " + user.calorie_lower_bound);
        temp.add("Sugar Lower Bound        \t\t: " + user.sugar_lower_bound);
        temp.add("Protein Upper Bound     \t \t: " + user.protein_upper_bound);
        temp.add("Fat Upper Bound         \t\t : " + user.fat_upper_bound);
        temp.add("Carbohydrate Upper Bound \t: " + user.carbohydrate_upper_bound);
        temp.add("Calorie Upper Bound      \t\t: " + user.calorie_upper_bound);
        temp.add("Sugar Upper Bound        \t\t: " + user.sugar_upper_bound);
        if(user.wanted_list.length != 0 )temp.add("Wanted Ingredients       \t\t: " + toString(user.wanted_list));
        if(user.unwanted_list.length != 0 )temp.add("Unwanted Ingredients       \t: " + toString(user.unwanted_list));

        return temp;
    }


    public String toString(String[] list) {
        String ret = "";
        for (int i = 0; i < list.length; i++){
            ret += list[i];
            if(i != list.length -1 ) {
                ret += ",";
            }
        }
        return  ret;
    }
    //TODO: Food server pages will be implemented.
    protected void setViewFoodServer(User user){}



    /**
     * this class prevents NetworkOnMainThreadException because it works asynchronous.
     *
     */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                Log.d("search", "onOptionsItemSelected: ");
                Intent i = new Intent(ProfilePageActivity.this, BasicSearch.class);
                startActivity(i);
                return true;
            case R.id.advanced_search:
                Intent ik = new Intent(ProfilePageActivity.this, AdvancedSearch.class);
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
