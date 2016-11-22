package com.knowwhatwoueat.kwue.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.SemanticTag;
import com.knowwhatwoueat.kwue.R;
import com.knowwhatwoueat.kwue.Utils.Constants;
import com.knowwhatwoueat.kwue.Utils.GsonRequest;

import java.util.ArrayList;

public class BasicSearch extends AppCompatActivity {
    private String basicSearch;
    private ListView basicSearchListView;


    private String searchQuery;

    private AlertDialog alertDialog;

    private RequestQueue queue;
    String url = Constants.endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_search);

        final EditText basicSearchTextBox = (EditText) findViewById(R.id.basicSearchTextBox);

        basicSearchListView = (ListView) findViewById(R.id.basicSearchList);

        final Button basicSearchButton = (Button) findViewById(R.id.basicSearchButton);


        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        final AlertDialog.Builder build = new AlertDialog.Builder(BasicSearch.this);
        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = build.create();
        LayoutInflater inflater = getLayoutInflater();
        View convertView =  inflater.inflate(R.layout.basic_search_list, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("List");
        ListView lv = (ListView) convertView.findViewById(R.id.listView3);



        basicSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSearchQuery(basicSearchTextBox.getText().toString());
                Log.d("search button", "onClick: clicked");
                showAlertDialog();

            }
        });
    }

    protected void setSearchQuery(String query) {
        searchQuery = query;
    }

    protected void showAlertDialog(){
        alertDialog.show();
    }


}
