package com.knowwhatwoueat.kwue.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.knowwhatwoueat.kwue.R;

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
        final EditText carbonhydrateUpperBoundTextbox =(EditText) findViewById(R.id.calorieUpperBound);
        final EditText calorieLowerBoundTextbox = (EditText) findViewById(R.id.calorieLowerBound);
        final EditText calorieUpperBoundTextbox =(EditText) findViewById(R.id.calorieUpperBound);
        final EditText sugarLowerBoundTextbox = (EditText) findViewById(R.id.sugarLowerBound);
        final EditText sugarUpperBoundTextbox =(EditText) findViewById(R.id.sugarUpperBound);
        final EditText wantedTextbox = (EditText) findViewById(R.id.wantedTextBox);
        final EditText unwantedTextbox =(EditText) findViewById(R.id.unwantedTextBox);

        Button advancedSearchButton = (Button) findViewById(R.id.advancedSearchButton);

        advancedSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advancedSearch = advancedsearchTextBox.getText().toString();
                proteinLowerBound = Integer.parseInt(proteinLowerBoundTextBox.getText().toString());
                proteinUpperBound = Integer.parseInt(proteinUpperBoundTextBox.getText().toString());
                fatLowerBound = Integer.parseInt(fatLowerBoundTextbox.getText().toString());
                fatUpperBound = Integer.parseInt(fatUpperBoundTextbox.getText().toString());
                carbonhydrateLowerBound = Integer.parseInt(carbonhydrateLowerBoundTextbox.getText().toString());
                carbonhydrateUpperBound = Integer.parseInt(calorieUpperBoundTextbox.getText().toString());
                sugarLowerBound = Integer.parseInt(sugarLowerBoundTextbox.getText().toString());
                sugarUpperBound = Integer.parseInt(sugarUpperBoundTextbox.getText().toString());
                wanted = wantedTextbox.getText().toString();
                unwanted = unwantedTextbox.getText().toString();
            }
        });

    }
}
