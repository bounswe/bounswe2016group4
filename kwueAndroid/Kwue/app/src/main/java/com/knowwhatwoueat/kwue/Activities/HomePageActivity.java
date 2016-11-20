package com.knowwhatwoueat.kwue.Activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.knowwhatwoueat.kwue.R;


import java.net.URL;

public class HomePageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ImageView firstRecommendedImage = (ImageView) findViewById(R.id.firstImage);
        ImageView secondRecommendedImage = (ImageView) findViewById(R.id.secondImage);
        ImageView thirdRecommendedImage = (ImageView) findViewById(R.id.thirdImage);
        ImageView forthRecommendedImage = (ImageView) findViewById(R.id.forthImage);
        ImageView fifthRecommendedImage = (ImageView) findViewById(R.id.fifthImage);
        firstRecommendedImage.setImageResource(R.mipmap.ic_launcher);
        secondRecommendedImage.setImageResource(R.mipmap.ic_launcher);
        thirdRecommendedImage.setImageResource(R.mipmap.ic_launcher);
        forthRecommendedImage.setImageResource(R.mipmap.ic_launcher);
        fifthRecommendedImage.setImageResource(R.mipmap.ic_launcher);
        TextView firstActivity = (TextView) findViewById(R.id.firstActivity);
        TextView secondActivity = (TextView) findViewById(R.id.secondActivity);
        TextView thirdActivity = (TextView) findViewById(R.id.thirdActivity);
        TextView forthActivity = (TextView) findViewById(R.id.forthActivity);
        TextView fifthActivity = (TextView) findViewById(R.id.fifthActivity);
        TextView sixthActivity = (TextView) findViewById(R.id.sixthActivity);
        firstActivity.setText("Activity1");
        secondActivity.setText("Activity2");
        thirdActivity.setText("Activity3");
        forthActivity.setText("Activity4");
        fifthActivity.setText("Activity5");
        sixthActivity.setText("Activity6");




        /*
        String emailText = getIntent().getStringExtra("EmailTextView");
        String passwordText = getIntent().getStringExtra("PasswordTextView");
        TextView emailTextView = (TextView) findViewById(R.id.emailTextView);
        TextView passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        emailTextView.setText(emailText);
        passwordTextView.setText(passwordText);
        */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        /*
        getMenuInflater().inflate(R.menu.options_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);

        */
        return true;
    }



    /*
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "Searching by: "+ query, Toast.LENGTH_SHORT).show();

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            Toast.makeText(this, "Suggestion: "+ uri, Toast.LENGTH_SHORT).show();
        }
    }
    */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
