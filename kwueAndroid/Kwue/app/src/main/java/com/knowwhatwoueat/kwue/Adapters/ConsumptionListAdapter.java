package com.knowwhatwoueat.kwue.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.R;


/**
 * Created by Mehmet Akif ÇÖRDÜK on 26.10.2016.
 */

public class ConsumptionListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public ConsumptionListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.consumption_history_list_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.consumption_history_list_item, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.FoodName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.Description);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText("Description "+itemname[position]);
        return rowView;

    };
}
