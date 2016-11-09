package com.knowwhatwoueat.kwue.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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




        /*
        String emailText = getIntent().getStringExtra("EmailTextView");
        String passwordText = getIntent().getStringExtra("PasswordTextView");
        TextView emailTextView = (TextView) findViewById(R.id.emailTextView);
        TextView passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        emailTextView.setText(emailText);
        passwordTextView.setText(passwordText);
        */
    }
}
