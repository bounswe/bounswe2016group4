package com.knowwhatwoueat.kwue.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.knowwhatwoueat.kwue.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText emailTextbox = (EditText) findViewById(R.id.emailTextbox);
        final EditText passwordTextbox = (EditText) findViewById(R.id.passwordTextbox);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button newUserButton = (Button) findViewById(R.id.newUserButton);


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = emailTextbox.getText().toString();
                String password = passwordTextbox.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast t;
                    if (email.isEmpty()) {
                        t = Toast.makeText(getApplicationContext(), "E-mail is required", Toast.LENGTH_SHORT);
                    } else {
                        t = Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT);
                    }
                    t.show();
                } else {

                    Intent i = new Intent(LoginActivity.this, ProfilePageActivity.class);
                    //i.putExtra("EmailTextView", email);
                    //i.putExtra("PasswordTextView", password);
                    startActivity(i);

                }
            }

        });

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
