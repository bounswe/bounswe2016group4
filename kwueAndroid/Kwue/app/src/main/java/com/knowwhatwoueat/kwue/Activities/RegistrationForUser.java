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

import com.knowwhatwoueat.kwue.R;

public class RegistrationForUser extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private String gender;
    private String day;
    private String month;
    private String year;
    private String city;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String passwordAgain;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterD;
    ArrayAdapter<String> adapterM;
    ArrayAdapter<String> adapterY;
    ArrayAdapter<String> adapterC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_for_user);

        final EditText nameTextBox = (EditText) findViewById(R.id.nameTextbox);
        final EditText surnameTextBox = (EditText) findViewById(R.id.surnameTextbox);
        final EditText emailTextBox = (EditText) findViewById(R.id.emailTextboxR);
        final EditText passwordTextbox = (EditText) findViewById(R.id.passwordTextboxR);
        final EditText passwordAgainTextbox =(EditText) findViewById(R.id.passwordAgainTextbox);



        final Spinner genderDropdown = (Spinner) findViewById(R.id.genderOption);
        String [] genderTypes= new String[] {"Select Gender","Male", "Female"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,genderTypes);
        genderDropdown.setAdapter(adapter);
        //gender = genderDropdown.getSelectedItem().toString();
        genderDropdown.setOnItemSelectedListener(this);


        final Spinner dayOption = (Spinner) findViewById(R.id.dayOption);
        String [] days = new String[32];
        days[0]="D";
        for(int i = 1;i<=31;i++){
            days[i]=i+"";
        }
        adapterD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,days);
        dayOption.setAdapter(adapterD);
        //day = dayOption.getSelectedItem().toString();
        dayOption.setOnItemSelectedListener(this);


        final Spinner monthOption = (Spinner) findViewById(R.id.monthOption);
        String [] months = new String[13];
        months[0]="M";
        for(int i = 1;i<=12;i++){
            months[i]=i+"";
        }
        adapterM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,months);
        monthOption.setAdapter(adapterM);
        //month =monthOption.getSelectedItem().toString();
        monthOption.setOnItemSelectedListener(this);


        final Spinner yearOption = (Spinner) findViewById(R.id.yearOption);
        String [] years = new String[52];
        years[0]="Y";
        for(int i = 1;i<=51;i++){
            years[i]=1950+i+"";
        }

        adapterY = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,years);
        yearOption.setAdapter(adapterY);
        //year = yearOption.getSelectedItem().toString();
        yearOption.setOnItemSelectedListener(this);


        final Spinner cityOption = (Spinner) findViewById(R.id.cityOption);
        String [] cities = new String[]{"City","İstanbul","Adana","Sakarya","Mersin","Denizli","Antalya"};
        adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,cities);
        cityOption.setAdapter(adapterC);
        //city = cityOption.getSelectedItem().toString();
        cityOption.setOnItemSelectedListener(this);

        Button createAccount = (Button) findViewById(R.id.createAccountButton);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = genderDropdown.getSelectedItem().toString();
                month =monthOption.getSelectedItem().toString();
                day = dayOption.getSelectedItem().toString();
                year = yearOption.getSelectedItem().toString();
                city = cityOption.getSelectedItem().toString();
                name = nameTextBox.getText().toString();
                surname = nameTextBox.getText().toString();
                email = emailTextBox.getText().toString();
                password = passwordTextbox.getText().toString();
                passwordAgain = passwordAgainTextbox.getText().toString();

                /*
                Intent i = new Intent(RegistrationForUser.this, RegistrationForFoodServer.class);

                i.putExtra("first", gender);
                i.putExtra("second", day);
                i.putExtra("third", name);
                startActivity(i);

                */

                Intent intent = new Intent(RegistrationForUser.this, HomeActivity.class);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
