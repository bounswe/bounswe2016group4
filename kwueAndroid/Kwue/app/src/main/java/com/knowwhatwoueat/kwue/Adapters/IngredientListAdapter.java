package com.knowwhatwoueat.kwue.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.Ingredient;
import com.knowwhatwoueat.kwue.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 19.11.2016.
 */

public class IngredientListAdapter extends ArrayAdapter<Ingredient> {
    private final Activity context;
    List<Ingredient> ingredients;
    public IngredientListAdapter(Activity context, List<Ingredient> ingredients) {
        super(context, R.layout.ingredient_list_item, ingredients);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.ingredients = ingredients;

    }
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.ingredient_list_item, parent,false);

        TextView txtIngredient = (TextView) rowView.findViewById(R.id.item_ingredient);
        TextView txtGrams = (TextView) rowView.findViewById(R.id.item_quantity_ingredient);
        Button deleteButton = (Button) rowView.findViewById(R.id.ingredient_delete_button);

        txtIngredient.setText(ingredients.get(position).getName());
        txtGrams.setText(String.valueOf(ingredients.get(position).getQuantity()));


        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ingredients.remove(position);
                notifyDataSetChanged();
                Log.d("tag", "onClick: clicked" );

            }

        });


        return rowView;

    }

    @Override
    public void add(Ingredient ingredient){
        ingredients.add(ingredient);
        notifyDataSetChanged();
    }
}