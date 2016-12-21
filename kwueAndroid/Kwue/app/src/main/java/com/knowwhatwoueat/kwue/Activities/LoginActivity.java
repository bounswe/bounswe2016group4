package com.knowwhatwoueat.kwue.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.DataModels.BasicSearchResult;
import com.knowwhatwoueat.kwue.DataModels.LoginResult;
import com.knowwhatwoueat.kwue.DataModels.Nutrition;
import com.knowwhatwoueat.kwue.DataModels.SessionResult;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import java.util.HashMap;
import java.util.Map;

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

        sendSessionRequest();

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
                    sendLoginHttpRequest();

                    Intent i = new Intent(LoginActivity.this, ProfilePageActivity.class);
                    i.putExtra("userId", userID);
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

    protected void sendLoginHttpRequest() {

        String loginUrl = url + "loginmobile";
        System.out.println(loginUrl);
        System.out.println(email);
        System.out.println(password);
        //final String semanticsResponse;
        Gson gson = new Gson();
        StringRequest sr = new StringRequest(Request.Method.POST,loginUrl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("login", "onResponse: success" + response);
                assingUserID(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", "That didn't work!");
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_email_address",email);
                params.put("user_password",password);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    protected void assingUserID(String response){
        Gson gson = new Gson();
        login = gson.fromJson(response,LoginResult.class);
        userID = login.getUserID();

    }



    protected void sendSessionRequest() {

        String sessionUrl = url + "create_session";


        // Request a string response from the provided URL.
        GsonRequest<SessionResult> gsonRequest = new GsonRequest<>(sessionUrl, SessionResult.class, Request.Method.GET,
                new Response.Listener<SessionResult>() {
                    @Override
                    public void onResponse(SessionResult response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: session");

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(gsonRequest);
    }
}
