package com.knowwhatwoueat.kwue.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.R;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 23.11.2016.
 */

public class SimpleNutritionAdapter extends ArrayAdapter<String> {
    List<String> simpleNutritions;
    Context context;
    String interval;
    public SimpleNutritionAdapter(Context context, int resource, List<String> simpleNutritions) {
        super(context, resource, simpleNutritions);
        this.simpleNutritions = simpleNutritions;
        this.context = context;
    }

    @Override
    public void add(String object) {
        simpleNutritions.add(object);
        notifyDataSetChanged();
    }

}
