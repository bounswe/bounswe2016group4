package com.knowwhatwoueat.kwue.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdvancedSearch extends AppCompatActivity {
    private String advancedSearch;
    private int proteinLowerBound=0;
    private int proteinUpperBound=1000;
    private int fatLowerBound=0;
    private int fatUpperBound=1000;
    private int carbonhydrateLowerBound=0;
    private int carbonhydrateUpperBound=1000;
    private int calorieLowerBound=0;
    private int calorieUpperBound=1000;
    private int sugarLowerBound=0;
    private int sugarUpperBound=1000;
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

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbarProtein = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbarProtein);
        rangeSeekbarProtein.setMinValue(0);
        rangeSeekbarProtein.setMaxValue(1000);
        rangeSeekbarProtein.setBarHighlightColor(Color.RED);
        rangeSeekbarProtein.setLeftThumbColor(Color.DKGRAY);
        rangeSeekbarProtein.setRightThumbColor(Color.DKGRAY);

        final CrystalRangeSeekbar rangeSeekbarFat = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbarFat);
        rangeSeekbarFat.setMinValue(0);
        rangeSeekbarFat.setMaxValue(1000);
        rangeSeekbarFat.setBarHighlightColor(Color.RED);
        rangeSeekbarFat.setLeftThumbColor(Color.DKGRAY);
        rangeSeekbarFat.setRightThumbColor(Color.DKGRAY);

        final CrystalRangeSeekbar rangeSeekbarCarbonhydrate = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbarCarbonHydrate);
        rangeSeekbarCarbonhydrate.setMinValue(0);
        rangeSeekbarCarbonhydrate.setMaxValue(1000);
        rangeSeekbarCarbonhydrate.setBarHighlightColor(Color.RED);
        rangeSeekbarCarbonhydrate.setLeftThumbColor(Color.DKGRAY);
        rangeSeekbarCarbonhydrate.setRightThumbColor(Color.DKGRAY);

        final CrystalRangeSeekbar rangeSeekbarCalorie = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbarCalorie);
        rangeSeekbarCalorie.setMinValue(0);
        rangeSeekbarCalorie.setMaxValue(1000);
        rangeSeekbarCalorie.setBarHighlightColor(Color.RED);
        rangeSeekbarCalorie.setLeftThumbColor(Color.DKGRAY);
        rangeSeekbarCalorie.setRightThumbColor(Color.DKGRAY);

        final CrystalRangeSeekbar rangeSeekbarSugar = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbarSugar);
        rangeSeekbarSugar.setMinValue(0);
        rangeSeekbarSugar.setMaxValue(1000);
        rangeSeekbarSugar.setBarHighlightColor(Color.RED);
        rangeSeekbarSugar.setLeftThumbColor(Color.DKGRAY);
        rangeSeekbarSugar.setRightThumbColor(Color.DKGRAY);



        // get min and max text view

        final TextView proteinLower = (TextView) findViewById(R.id.proteinLowerBoundTextBox);
        final TextView proteinUpper = (TextView) findViewById(R.id.proteinUpperBoundTextBox);
        final TextView fatLower = (TextView) findViewById(R.id.fatLowerBoundTextBox);
        final TextView fatUpper = (TextView) findViewById(R.id.fatUpperBoundTextBox);
        final TextView carbonhydrateLower = (TextView) findViewById(R.id.carbonhydrateLowerBoundTextBox);
        final TextView carbonhydrateUpper = (TextView) findViewById(R.id.carbonhydrateUpperBoundTextBox);
        final TextView calorieLower = (TextView) findViewById(R.id.calorieLowerBoundTextBox);
        final TextView calorieUpper = (TextView) findViewById(R.id.calorieUpperBoundTextBox);
        final TextView sugarLower = (TextView) findViewById(R.id.sugarLowerBoundTextBox);
        final TextView sugarUpper = (TextView) findViewById(R.id.sugarUpperBoundTextBox);


        // set listener
        rangeSeekbarProtein.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                proteinLower.setText(String.valueOf(minValue));
                proteinUpper.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbarProtein.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                String x = String.valueOf(minValue);
                String y = String.valueOf(maxValue);
                proteinLowerBound = Integer.parseInt(x);
                proteinUpperBound = Integer.parseInt(y);

                //Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        // set listener
        rangeSeekbarFat.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                fatLower.setText(String.valueOf(minValue));
                fatUpper.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbarFat.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                String x = String.valueOf(minValue);
                String y = String.valueOf(maxValue);
                fatLowerBound= Integer.parseInt(x);
                fatUpperBound = Integer.parseInt(y);

                //Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        // set listener
        rangeSeekbarCarbonhydrate.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                carbonhydrateLower.setText(String.valueOf(minValue));
                carbonhydrateUpper.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbarCarbonhydrate.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                String x = String.valueOf(minValue);
                String y = String.valueOf(maxValue);
                carbonhydrateLowerBound = Integer.parseInt(x);
                carbonhydrateUpperBound = Integer.parseInt(y);
                //Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        // set listener
        rangeSeekbarCalorie.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                calorieLower.setText(String.valueOf(minValue));
                calorieUpper.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbarCalorie.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                String x = String.valueOf(minValue);
                String y = String.valueOf(maxValue);
                calorieLowerBound = Integer.parseInt(x);
                calorieUpperBound = Integer.parseInt(y);

                //Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });

        // set listener
        rangeSeekbarSugar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                sugarLower.setText(String.valueOf(minValue));
                sugarUpper.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbarSugar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                String x = String.valueOf(minValue);
                String y = String.valueOf(maxValue);
                sugarLowerBound = Integer.parseInt(x);
                sugarUpperBound = Integer.parseInt(y);
                //Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });



        final EditText advancedsearchTextBox = (EditText) findViewById(R.id.advancedSearchTextBox);
        final EditText wantedTextbox = (EditText) findViewById(R.id.wantedTextBox);
        final EditText unwantedTextbox = (EditText) findViewById(R.id.unwantedTextBox);

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

                advancedSearch = advancedsearchTextBox.getText().toString();
                wanted = wantedTextbox.getText().toString();
                unwanted = unwantedTextbox.getText().toString();

                searchQuery = "advanced_search?search_text=" + advancedSearch + "&protein_lower_bound=" + proteinLowerBound +
                        "&fat_lower_bound=" + fatLowerBound + "&carbohydrate_lower_bound=" + carbonhydrateLowerBound +
                        "&calorie_lower_bound=" + calorieLowerBound + "&sugar_lower_bound=" + sugarLowerBound +
                        "&protein_upper_bound=" + proteinUpperBound + "&fat_upper_bound=" + fatUpperBound +
                        "&carbohydrate_upper_bound=" + carbonhydrateUpperBound + "&calorie_upper_bound=" + calorieUpperBound +
                        "&sugar_upper_bound=" + sugarUpperBound + "&wanted_list=" + wanted + "&unwanted_list=" + unwanted;

                Log.d("search button", "onClick: clicked");

                System.out.println(searchQuery);

            }
        });

    }


    protected void showAlertDialog() {
        alertDialog.show();
    }
}


