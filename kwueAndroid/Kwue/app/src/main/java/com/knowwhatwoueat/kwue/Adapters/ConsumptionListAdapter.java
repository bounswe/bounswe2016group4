package com.knowwhatwoueat.kwue.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.Activities.ConsumptionHistoryActivity;
import com.knowwhatwoueat.kwue.DataModels.Food;
import com.knowwhatwoueat.kwue.R;

import java.net.URL;
import java.util.List;

import com.nostra13.universalimageloader.*;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 26.10.2016.
 */

public class ConsumptionListAdapter extends ArrayAdapter<Food> {
    private final Activity context;
   //private final String[] itemname;
    //private final String[] imgurls;
    private final ImageLoader imageLoader ;
    private final List<Food> foods;
    public ConsumptionListAdapter(Activity context, List<Food> foods) {
        super(context, R.layout.consumption_history_list_item, foods);
        // TODO Auto-generated constructor stub

        imageLoader =ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        this.context=context;
        //this.itemname=foods.;
        //this.imgurls=imgurl;
        this.foods = foods;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.consumption_history_list_item, parent,false);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.FoodName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.FoodThumbNail);
        TextView extratxt = (TextView) rowView.findViewById(R.id.Description);
        imageLoader.displayImage(foods.get(position).getImageUrl(),imageView);

        txtTitle.setText(foods.get(position).getName());
        extratxt.setText("Description "+foods.get(position).getInfo());
        return rowView;

    }
}
