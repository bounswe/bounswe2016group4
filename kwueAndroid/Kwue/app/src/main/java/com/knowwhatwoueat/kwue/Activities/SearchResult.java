package com.knowwhatwoueat.kwue.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.knowwhatwoueat.kwue.DataModels.FoodBasicSearch;
import com.knowwhatwoueat.kwue.DataModels.FoodServerBasicSearch;
import com.knowwhatwoueat.kwue.R;

public class SearchResult extends AppCompatActivity {

    public FoodServerBasicSearch[] user_set;
    public FoodServerBasicSearch[] semantic_user_set;
    public FoodBasicSearch[] semantic_food_set;
    public FoodBasicSearch[] food_set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        /*
        i.putExtra("FoodSet", food_set);
        i.putExtra("SemanticFoodSet", semantic_food_set);
        i.putExtra("UserSet",user_set);
        i.putExtra("SemanticUserSet",semantic_user_set);
        */



    }
}
