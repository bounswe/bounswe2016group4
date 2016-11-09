package com.knowwhatwoueat.kwue.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.R;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        String emailText = getIntent().getStringExtra("EmailTextView");
        String passwordText = getIntent().getStringExtra("PasswordTextView");
        TextView emailTextView = (TextView) findViewById(R.id.emailTextView);
        TextView passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        emailTextView.setText(emailText);
        passwordTextView.setText(passwordText);
    }
}
