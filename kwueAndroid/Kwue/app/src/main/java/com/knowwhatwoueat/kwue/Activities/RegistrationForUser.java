package com.knowwhatwoueat.kwue.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.knowwhatwoueat.kwue.R;

public class RegistrationForUser extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_for_user);



        Spinner genderDropdown = (Spinner) findViewById(R.id.genderOption);
        String [] genderTypes= new String[] {"Select Gender","Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,genderTypes);
        genderDropdown.setAdapter(adapter);
        genderDropdown.setOnItemSelectedListener(this);


        Spinner dayOption = (Spinner) findViewById(R.id.dayOption);
        String [] days = new String[32];
        days[0]="D";
        for(int i = 1;i<=31;i++){
            days[i]=i+"";
        }
        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,days);
        dayOption.setAdapter(adapterD);
        dayOption.setOnItemSelectedListener(this);


        Spinner monthOption = (Spinner) findViewById(R.id.monthOption);
        String [] months = new String[13];
        months[0]="M";
        for(int i = 1;i<=12;i++){
            months[i]=i+"";
        }
        ArrayAdapter<String> adapterM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,months);
        monthOption.setAdapter(adapterM);
        monthOption.setOnItemSelectedListener(this);


        Spinner yearOption = (Spinner) findViewById(R.id.yearOption);
        String [] years = new String[52];
        years[0]="Y";
        for(int i = 1;i<=51;i++){
            years[i]=1950+i+"";
        }
        ArrayAdapter<String> adapterY = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,years);
        yearOption.setAdapter(adapterY);
        yearOption.setOnItemSelectedListener(this);


        Spinner cityOption = (Spinner) findViewById(R.id.cityOption);
        String [] cities = new String[]{"İstanbul","Adana","Sakarya","Mersin","Denizli","Antalya"};
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,cities);
        cityOption.setAdapter(adapterC);
        cityOption.setOnItemSelectedListener(this);

        Button createAccount = (Button) findViewById(R.id.createAccountButton);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationForUser.this, HomePageActivity.class);
                startActivity(i);

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        System.out.println(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
