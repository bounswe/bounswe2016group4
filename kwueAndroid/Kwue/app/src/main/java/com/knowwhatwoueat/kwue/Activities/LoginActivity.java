package com.knowwhatwoueat.kwue.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.DataModels.LoginResult;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;

public class LoginActivity extends AppCompatActivity {

    private String email;
    private String password;
    private int userID;
    private LoginResult login;

    private RequestQueue queue;
    String url = Constants.endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();



        final EditText emailTextbox = (EditText) findViewById(R.id.emailTextbox);
        final EditText passwordTextbox = (EditText) findViewById(R.id.passwordTextbox);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button newUserButton = (Button) findViewById(R.id.newUserButton);


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email = emailTextbox.getText().toString();
                password = passwordTextbox.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast t;
                    if (email.isEmpty()) {
                        t = Toast.makeText(getApplicationContext(), "E-mail is required", Toast.LENGTH_SHORT);
                    } else {
                        t = Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT);
                    }
                    t.show();
                } else {

                    Log.d("login", "onClick: clicked");
                    //sendLoginHttpRequest();

                    Intent i = new Intent(LoginActivity.this, ProfilePageActivity.class);
                    //i.putExtra("userId", "1");
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
        sendSessionRequest();
    }

    protected void sendLoginHttpRequest() {

        String loginUrl = url + "android_login?user_email_address="+email+"&user_password="+password;

        System.out.println(loginUrl);
        System.out.println(email);
        System.out.println(password);

        Gson gson = new Gson();
        StringRequest sr = new StringRequest(Request.Method.GET,loginUrl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("login", "onResponse: success" + response);
                assingUserID(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response login", "That didn't work!");
            }
        });
        queue.add(sr);
    }

    protected void assingUserID(String response){
        Gson gson = new Gson();
        login = gson.fromJson(response,LoginResult.class);
        userID = login.getUserID();
    }



    protected void sendSessionRequest() {

        String sessionUrl = url + "create_session";
        Log.d("session", "sendSessionRequest: " + sessionUrl);

        // Request a string response from the provided URL.
        StringRequest sr = new StringRequest(Request.Method.GET,sessionUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("responsesession", "onResponse: session   " + response);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response session", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(sr);
    }
}
