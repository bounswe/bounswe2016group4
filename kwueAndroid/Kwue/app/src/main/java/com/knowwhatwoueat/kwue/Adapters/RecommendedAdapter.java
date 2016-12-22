package com.knowwhatwoueat.kwue.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.DataModels.RecommendedFoods;
import com.knowwhatwoueat.kwue.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 22.12.2016.
 */

public class RecommendedAdapter extends ArrayAdapter<RecommendedFoods> {
    private final Activity context;
    //private final String[] itemname;
    //private final String[] imgurls;
    private final ImageLoader imageLoader;
    private final List<RecommendedFoods> foods;

    public RecommendedAdapter(Activity context, List<RecommendedFoods> foods) {
        super(context, R.layout.consumption_history_list_item, foods);
        // TODO Auto-generated constructor stub

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        this.context = context;
        //this.itemname=foods.;
        //this.imgurls=imgurl;
        this.foods = foods;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.consumption_history_list_item, parent, false);

        TextView nameView = (TextView) rowView.findViewById(R.id.FoodName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.FoodThumbNail);
        //TextView descriptionView = (TextView) rowView.findViewById(R.id.Description);
        TextView timeView = (TextView) rowView.findViewById(R.id.conusmptionTimeAdded);

        imageLoader.displayImage(foods.get(position).getFood_image(), imageView);
        nameView.setText(foods.get(position).getFood_name());

        return rowView;

    }

    public void dataReceived() {
        notifyDataSetChanged();
    }
}