package com.knowwhatwoueat.kwue.Adapters;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.DataModels.Tag;
import com.knowwhatwoueat.kwue.R;

import java.util.List;

/**
 * Created by Gokberk on 21.12.2016.
 */

public class TagsListAdapter extends ArrayAdapter<Tag> {

    private final Activity context ;
    private final Tag[] tags ;

    public TagsListAdapter(Activity context, Tag[] tags) {
        super(context, R.layout.tags_list_item , tags);

        this.context = context;
        this.tags = tags;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.tags_list_item, parent,false);

        TextView textViewItem = (TextView) rowView.findViewById(R.id.tag_name);
        TextView textViewSubItem = (TextView) rowView.findViewById(R.id.tag_description);

        textViewItem.setText(tags[position].getTag_label());
        textViewSubItem.setText(tags[position].getTag_description());


        return rowView;
    }
}
