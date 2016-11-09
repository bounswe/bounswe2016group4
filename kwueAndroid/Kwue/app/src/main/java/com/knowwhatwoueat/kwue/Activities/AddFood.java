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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.R;

public class AddFood extends AppCompatActivity {
    private EditText editFoodTextBox;
    private EditText editDescriptionTextBox;
    private Button getTagsButton;
    private EditText semanticTextBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton sendFoodButton = (FloatingActionButton) findViewById(R.id.fab);
        sendFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        editFoodTextBox = (EditText) findViewById(R.id.add_food_name);
        editDescriptionTextBox = (EditText) findViewById(R.id.food_description);
        getTagsButton= (Button) findViewById(R.id.semantic_button);
        semanticTextBox = (EditText) findViewById(R.id.semantic_tag_text_box);

        getTagsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String semanticQuery = semanticTextBox.getText().toString();
                Log.d("tag", "onClick: clicked" + semanticQuery);
            }

        });

    }
}
