package com.knowwhatwoueat.kwue.Activities;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.knowwhatwoueat.kwue.DataModels.Server;
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


    private RequestQueue queue ;
    String url = Constants.endPoint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);



        //Remove the hardcode
        user.user_name = "Suzan";
        user.user_type = 0 ;
        addDummyFood();

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

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();




    }

/*
    protected void requestUser(int userId){
        String user_url = url + "get_user?=user_id" + userId;

        GsonRequest<User> gsonRequest = new GsonRequest<>(user_url,User.class, Request.Method.GET,
                new Response.Listener<SemanticTag[]>() {
                    @Override
                    public void onResponse(SemanticTag[] response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: in");
                        assignTagTitles(response);
                        showAlertDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response","That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(gsonRequest);
    }

    protected void showAlertDialog(){
        alertDialog.show();
    }
*/












    private void addDummyFood(){
        consumptionHistory = new ArrayList<Food>();
        String url =  "http://adanamreklam.com/firmalar/s_176_Adana.png";
        /*
        consumptionHistory.add(new Food(new Server(),"description","Balik",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Kebap",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Tatlı",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Su",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Kola",null,null,null,url));
        consumptionHistory.add(new Food(new Server(),"description","Sandviç",null,null,null,url));
        */
    }
    /*
    private String[] getFoodNames(){
        String[] foods = new String[consumptionHistory.size()];
        for(int i = 0 ; i< consumptionHistory.size();i++){
            foods[i] = consumptionHistory.get(i).getName();
        }
        return foods;
    }
    private String[] getImageUrls(){
        String[] urls = new String[consumptionHistory.size()];

        for(int i = 0 ; i< consumptionHistory.size();i++){
            urls[i] = consumptionHistory.get(i).getImageUrl();
        }
        return urls;
    }


*/

}
