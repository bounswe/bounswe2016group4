package com.knowwhatwoueat.kwue.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;

import java.util.ArrayList;

public class AdvancedSearch extends AppCompatActivity {
    private String advancedSearch;
    private int proteinLowerBound;
    private int proteinUpperBound;
    private int fatLowerBound;
    private int fatUpperBound;
    private int carbonhydrateLowerBound;
    private int carbonhydrateUpperBound;
    private int calorieLowerBound;
    private int calorieUpperBound;
    private int sugarLowerBound;
    private int sugarUpperBound;
    private String wanted;
    private String unwanted;


    private ListView advancedSearchListView;

    private String searchQuery;

    private ArrayAdapter searchListAdapter;

    private ArrayList<String> responseNamesList;

    private AlertDialog alertDialog;

    private RequestQueue queue;
    String url = Constants.endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        final EditText advancedsearchTextBox = (EditText) findViewById(R.id.advancedSearchTextBox);

        final EditText proteinLowerBoundTextBox = (EditText) findViewById(R.id.proteinLowerBound);
        final EditText proteinUpperBoundTextBox = (EditText) findViewById(R.id.proteinUpperBound);

        final EditText fatLowerBoundTextbox = (EditText) findViewById(R.id.fatLowerBound);
        final EditText fatUpperBoundTextbox =(EditText) findViewById(R.id.fatUpperBoun);

        final EditText carbonhydrateLowerBoundTextbox = (EditText) findViewById(R.id.carbonhydrateLowerBoun);
        final EditText carbonhydrateUpperBoundTextbox =(EditText) findViewById(R.id.carbonhydrateUpperBoun);

        final EditText calorieLowerBoundTextbox = (EditText) findViewById(R.id.calorieLowerBound);
        final EditText calorieUpperBoundTextbox =(EditText) findViewById(R.id.calorieUpperBound);

        final EditText sugarLowerBoundTextbox = (EditText) findViewById(R.id.sugarLowerBound);
        final EditText sugarUpperBoundTextbox =(EditText) findViewById(R.id.sugarUpperBound);

        final EditText wantedTextbox = (EditText) findViewById(R.id.wantedTextBox);
        final EditText unwantedTextbox =(EditText) findViewById(R.id.unwantedTextBox);

        advancedSearchListView = (ListView) findViewById(R.id.advancedSearchList);

        final Button advancedSearchButton = (Button) findViewById(R.id.advancedSearchButton);

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        responseNamesList = new ArrayList<String>();

        searchListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, responseNamesList);

        final AlertDialog.Builder build = new AlertDialog.Builder(AdvancedSearch.this);
        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = build.create();
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.advanced_search_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("List");
        ListView lv = (ListView) convertView.findViewById(R.id.listView4);
        lv.setAdapter(searchListAdapter);


        advancedSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responseNamesList.clear();

                proteinLowerBound=0;
                proteinUpperBound=0;
                fatLowerBound=0;
                fatUpperBound=0;
                carbonhydrateLowerBound=0;
                carbonhydrateUpperBound=0;
                calorieLowerBound=0;
                calorieUpperBound=0;
                sugarLowerBound=0;
                sugarUpperBound=0;

                if(proteinLowerBoundTextBox.getText().length()>1)
                    proteinLowerBound= Integer.valueOf(proteinLowerBoundTextBox.getText().toString());
                if(proteinUpperBoundTextBox.getText().length()>1)
                    proteinUpperBound= Integer.valueOf(proteinUpperBoundTextBox.getText().toString());
                if(fatLowerBoundTextbox.getText().length()>1)
                    fatLowerBound= Integer.valueOf(fatLowerBoundTextbox.getText().toString());
                if(fatUpperBoundTextbox.getText().length()>1)
                    fatUpperBound= Integer.valueOf(fatUpperBoundTextbox.getText().toString());
                if(carbonhydrateLowerBoundTextbox.getText().length()>1)
                    carbonhydrateLowerBound= Integer.valueOf(carbonhydrateLowerBoundTextbox.getText().toString());
                if(carbonhydrateUpperBoundTextbox.getText().length()>1)
                    carbonhydrateUpperBound= Integer.valueOf(carbonhydrateUpperBoundTextbox.getText().toString());
                if(calorieLowerBoundTextbox.getText().length()>1)
                    calorieLowerBound= Integer.valueOf(calorieLowerBoundTextbox.getText().toString());
                if(calorieUpperBoundTextbox.getText().length()>1)
                    calorieUpperBound= Integer.valueOf(calorieUpperBoundTextbox.getText().toString());
                if(sugarLowerBoundTextbox.getText().length()>1)
                    sugarLowerBound= Integer.valueOf(sugarLowerBoundTextbox.getText().toString());
                if(sugarUpperBoundTextbox.getText().length()>1)
                    sugarUpperBound= Integer.valueOf(sugarUpperBoundTextbox.getText().toString());

                advancedSearch = advancedsearchTextBox.getText().toString();
                wanted = wantedTextbox.getText().toString();
                unwanted = unwantedTextbox.getText().toString();
                /*
                searchQuery = "advanced_search?search_text="+advancedSearch+"&protein_lower_bound="+proteinLowerBound+
                        "&fat_lower_bound="+fatLowerBound+"&carbohydrate_lower_bound="+carbonhydrateLowerBound+
                        "&calorie_lower_bound="+calorieLowerBound+"&sugar_lower_bound="+sugarLowerBound+
                        "&protein_upper_bound="+proteinUpperBound+"&fat_upper_bound="+fatUpperBound+
                        "&carbohydrate_upper_bound="+carbonhydrateUpperBound+"&calorie_upper_bound="+calorieUpperBound+
                        "&sugar_upper_bound="+sugarUpperBound+"&wanted_list="+wanted+"&unwanted_list="+unwanted;
                */
                Log.d("search button", "onClick: clicked");

                //System.out.println(searchQuery);

            }
        });

    }


    protected void showAlertDialog() {
        alertDialog.show();
    }
}


