package com.knowwhatwoueat.kwue.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.DataModels.EatingPreferences;
import com.knowwhatwoueat.kwue.R;

import java.util.List;


public class EatingPreferencesAdapter extends ArrayAdapter<EatingPreferences> {
    private final Activity context ;
    private final List<EatingPreferences> eatPref ;

    public EatingPreferencesAdapter(Activity context, List<EatingPreferences> eatPref) {
        super(context, R.layout.content_eating_preferences, eatPref);

        this.context = context ;
        this.eatPref = eatPref;
    }

    public View getView(int position, View view , ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.content_eating_preferences, parent,false);



    /*
        TextView txtTitle = (TextView) rowView.findViewById(R.id.FoodName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.FoodThumbNail);
        TextView extratxt = (TextView) rowView.findViewById(R.id.Description);

        txtTitle.setText(foods.get(position).getName());
        extratxt.setText("Description "+foods.get(position).getInfo());*/
        return rowView;



    }


}
