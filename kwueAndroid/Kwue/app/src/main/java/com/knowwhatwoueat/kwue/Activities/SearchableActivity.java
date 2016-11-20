package com.knowwhatwoueat.kwue.Activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.knowwhatwoueat.kwue.R;

public class SearchableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            showResults(query);
        }
    }

    private void showResults(String query) {
        // Query your data set and show results
        // ...
    }
}
