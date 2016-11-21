package com.knowwhatwoueat.kwue.Activities;

import android.app.ActionBar;
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

public class HomePageActivity extends AppCompatActivity {

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

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_button, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_basicSearch:
                Intent i = new Intent(HomePageActivity.this, BasicSearch.class);
                startActivity(i);
                break;
            case R.id.action_advancedSearch:
                Intent j = new Intent(HomePageActivity.this, AdvancedSearch.class);
                startActivity(j);
                break;

            default:
                break;
        }
        return true;
    }


}
