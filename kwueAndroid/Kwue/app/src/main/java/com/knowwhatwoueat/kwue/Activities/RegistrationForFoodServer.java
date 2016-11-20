package com.knowwhatwoueat.kwue.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.R;

import org.w3c.dom.Text;

public class RegistrationForFoodServer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_for_food_server);

        String genderText = getIntent().getStringExtra("first");
        String dayText = getIntent().getStringExtra("second");
        String nameText = getIntent().getStringExtra("third");
        TextView genderTextView = (TextView) findViewById(R.id.first);
        TextView dayTextView = (TextView) findViewById(R.id.second);
        TextView nameTextView = (TextView) findViewById(R.id.third);
        genderTextView.setText(genderText);
        dayTextView.setText(dayText);
        nameTextView.setText(nameText);
    }
}
