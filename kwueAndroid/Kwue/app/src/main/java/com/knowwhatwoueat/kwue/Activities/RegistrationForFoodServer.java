package com.knowwhatwoueat.kwue.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.R;

import org.w3c.dom.Text;

public class RegistrationForFoodServer extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    private String restaurantName;
    private String restaurantDescription;
    private String restaurantAddress;
    private String emailFoodServer;
    private String passwordFoodServer;
    private String passwordAgainFoodServer;
    private String cityFoodServer;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_for_food_server);

        final EditText restaurantNameTextBox = (EditText) findViewById(R.id.restaurantNameTextbox);
        final EditText restaurantDescriptionTextBox = (EditText) findViewById(R.id.restaurantDescriptionTextbox);
        final EditText restaurantAddressTextBox = (EditText) findViewById(R.id.restaurantAddressTextbox);
        final EditText emailFoodServerTextBox = (EditText) findViewById(R.id.emailFoodServerTextbox);
        final EditText passwordFoodServerTextbox = (EditText) findViewById(R.id.passwordFoodServerTextbox);
        final EditText passwordAgainFoodServerTextbox =(EditText) findViewById(R.id.passwordAgainFoodServerTextbox);


        final Spinner cityOptionFoodServer = (Spinner) findViewById(R.id.cityOptionFoodServer);
        String [] cities = new String[]{"City","İstanbul","Adana","Sakarya","Mersin","Denizli","Antalya"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,cities);
        cityOptionFoodServer.setAdapter(adapter);
        //city = cityOption.getSelectedItem().toString();
        cityOptionFoodServer.setOnItemSelectedListener(this);



        Button createAccountFoodServer = (Button) findViewById(R.id.createAccountFoodServerButton);

        createAccountFoodServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restaurantName = restaurantNameTextBox.getText().toString();
                restaurantDescription = restaurantDescriptionTextBox.getText().toString();
                restaurantAddress = restaurantAddressTextBox.getText().toString();
                emailFoodServer = emailFoodServerTextBox.getText().toString();
                passwordFoodServer = passwordAgainFoodServerTextbox.getText().toString();
                passwordAgainFoodServer = passwordAgainFoodServerTextbox.getText().toString();
                cityFoodServer = cityOptionFoodServer.getSelectedItem().toString();

                Intent intent = new Intent(RegistrationForFoodServer.this, HomeActivity.class);
                startActivity(intent);


            }
        });


        /*
        String genderText = getIntent().getStringExtra("first");
        String dayText = getIntent().getStringExtra("second");
        String nameText = getIntent().getStringExtra("third");
        TextView genderTextView = (TextView) findViewById(R.id.first);
        TextView dayTextView = (TextView) findViewById(R.id.second);
        TextView nameTextView = (TextView) findViewById(R.id.third);
        genderTextView.setText(genderText);
        dayTextView.setText(dayText);
        nameTextView.setText(nameText);

        */
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
