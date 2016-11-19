package com.knowwhatwoueat.kwue.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 19.11.2016.
 */

public class IngredientListAdapter extends ArrayAdapter<Food> {
    private final Activity context;

    public IngredientListAdapter(Activity context, List<Food> foods) {
        super(context, R.layout.consumption_history_list_item, foods);
        // TODO Auto-generated constructor stub
        this.context = context;

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.ingredient_list_item, parent,false);


        return rowView;

    }
}