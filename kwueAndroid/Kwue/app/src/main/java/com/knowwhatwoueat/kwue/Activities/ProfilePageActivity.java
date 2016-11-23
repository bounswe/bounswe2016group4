package com.knowwhatwoueat.kwue.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.knowwhatwoueat.kwue.Adapters.ConsumptionListAdapter;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.User;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gökberk Erüst on 22.11.2016
 */
public class ProfilePageActivity extends AppCompatActivity {

    public static User user = new User();
    int userId = 1 ;
    public List<Food> consumptionHistory ;
    private RequestQueue queue;
    String url = Constants.endPoint;

    public static String intervalChoise = "daily" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        queue = Volley.newRequestQueue(this);



        consumptionHistory = new ArrayList<>();

        requestUser(userId);

        Log.d("print", "deneme: " + user.user_name);
        Log.d("print", "deneme: " + user.user_email_address);




    }


    protected void requestUser(int userId){
        String userUrl = url + "get_user?user_id=" + userId;

        GsonRequest<User> gsonRequest = new GsonRequest<>(userUrl,User.class, Request.Method.GET,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        // Display the first 500 characters of the response string.
                        Log.d("response", "onResponse: in" + response);
                        if(!response.user_type) {
                            setViewNormalUser(response);
                        }else{
                            setViewFoodServer(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response","That didn't work!" + error);
            }
        });
        queue.add(gsonRequest);
    }










    protected void setViewNormalUser(User user) {

        new DownloadImageTask((ImageView) findViewById(R.id.user_profile_image))
                .execute(user.user_image);

        TextView userName =(TextView) this.findViewById(R.id.user_name);
        userName.setText(user.user_name);

        TextView userMailAddress =(TextView) this.findViewById(R.id.user_email_address);
        userMailAddress.setText(user.user_email_address);

        ListView lw = (ListView) this.findViewById(R.id.consumption_history);
        ListAdapter listAdapter = new ConsumptionListAdapter(this,consumptionHistory);
        lw.setAdapter(listAdapter);

        Spinner dropdownConsumptionInterval = (Spinner) this.findViewById(R.id.consumption_history_interval);
        String[] items = new String[]{"daily","weekly","monthly","alltime"};
        ArrayAdapter<String> intervalAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        intervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownConsumptionInterval.setAdapter(intervalAdapter);
        dropdownConsumptionInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intervalChoise = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        ListView lw2 = (ListView) this.findViewById(R.id.user_eating_preferences);


        Button consumptionButton = (Button) findViewById(R.id.all_consumption_history);
        consumptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfilePageActivity.this, ConsumptionHistoryActivity.class);
                i.putExtra("consumption_history_interval", intervalChoise);
                startActivity(i);
            }
        });

    }








    protected void setViewFoodServer(User user){


    }



    /**
     * this class prevents NetworkOnMainThreadException because it works asynchronous.
     *
     */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }












}
