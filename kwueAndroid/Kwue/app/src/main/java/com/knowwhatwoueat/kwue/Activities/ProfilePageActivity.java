package com.knowwhatwoueat.kwue.Activities;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.SemanticTag;
import com.knowwhatwoueat.kwue.DataModels.User;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gökberk Erüst on 22.11.2016
 */
public class ProfilePageActivity extends AppCompatActivity {

    public User user = new User();
    int userId = 1 ;
    public List<Food> consumptionHistory ;

    private AlertDialog alertDialog;
    private RequestQueue queue;
    String url = Constants.endPoint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        queue = Volley.newRequestQueue(this);
        final AlertDialog.Builder build = new AlertDialog.Builder(ProfilePageActivity.this);
        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = build.create();




        consumptionHistory = new ArrayList<>();

        requestUser(userId);


        TextView userName =(TextView) this.findViewById(R.id.user_name);
        userName.setText(user.user_name);

        ListView lw = (ListView) this.findViewById(R.id.consumption_history);
        ListView lw2 = (ListView) this.findViewById(R.id.user_eating_preferences);

        ListAdapter listAdapter = new ConsumptionListAdapter(this,consumptionHistory);
        lw.setAdapter(listAdapter);

        Button consumptionButton = (Button) findViewById(R.id.all_consumption_history);
        consumptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfilePageActivity.this, ConsumptionHistoryActivity.class);
                startActivity(i);
            }
        });



    }
    protected void assignUserInfo(User response){
        //user = response;
        Log.d("print", "assignUserInfo: " + response.user_email_address);
        Log.d("print", "assignUserInfo: " + response.user_name);
        user.user_name = response.user_name;
    }

    protected void requestUser(int userId){
        String user_url = url + "get_user?user_id=" + userId;

        GsonRequest<User> gsonRequest = new GsonRequest<>(user_url,User.class, Request.Method.GET,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: in" + response);
                        assignUserInfo(response);
                        showAlertDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response","That didn't work!" + error);
            }
        });
// Add the request to the RequestQueue.
      queue.add(gsonRequest);
    }

    protected void showAlertDialog(){
        alertDialog.show();
    }













}
