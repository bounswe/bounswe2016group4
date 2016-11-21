package com.knowwhatwoueat.kwue.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.knowwhatwoueat.kwue.DataModels.SemanticTag;
import com.knowwhatwoueat.kwue.R;

import java.util.ArrayList;

public class BasicSearch extends AppCompatActivity {
    private String basicSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_search);

        final EditText basicSearchTextBox = (EditText) findViewById(R.id.basicSearchTextBox);

        final Button basicSearchButton = (Button) findViewById(R.id.basicSearchButton);

        basicSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basicSearch = basicSearchTextBox.getText().toString();
            }
        });
    }
}
