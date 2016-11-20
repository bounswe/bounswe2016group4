package com.knowwhatwoueat.kwue.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.knowwhatwoueat.kwue.R;

public class RegisterActivity extends AppCompatActivity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner userTypeDropdown = (Spinner) findViewById(R.id.userTypeOption);
        String [] userTypes= new String[] {"Select User Type","Food Server", "Normal User"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,userTypes);
        userTypeDropdown.setAdapter(adapter);
        userTypeDropdown.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Intent intent;
        if(item.equals("Food Server")) {
             intent = new Intent(RegisterActivity.this, RegistrationForFoodServer.class);
            startActivity(intent);
        }else if(item.equals("Normal User")){
             intent = new Intent(RegisterActivity.this, RegistrationForUser.class);
            startActivity(intent);
        }


        //Toast.makeText(adapterView.getContext(),"Selected: "+item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
