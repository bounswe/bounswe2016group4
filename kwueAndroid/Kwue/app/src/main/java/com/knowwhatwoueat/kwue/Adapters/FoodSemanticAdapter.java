package com.knowwhatwoueat.kwue.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.knowwhatwoueat.kwue.DataModels.Ingredient;
import com.knowwhatwoueat.kwue.DataModels.SemanticTag;
import com.knowwhatwoueat.kwue.R;

import java.util.List;


/**
 * Created by Mehmet Akif ÇÖRDÜK on 7.12.2016.
 */

public class FoodSemanticAdapter extends ArrayAdapter<SemanticTag> {
    private final Activity context;
    List<SemanticTag> tags;
    public FoodSemanticAdapter(Activity context, List<SemanticTag> tags){
        super(context, R.layout.semantic_list_item, tags);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.tags = tags;
    }
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.semantic_list_item, parent,false);

        TextView txtTag = (TextView) rowView.findViewById(R.id.tagnameBox);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.tagdescriptionBox);
        Button deleteButton = (Button) rowView.findViewById(R.id.ingredient_delete_button);

        txtTag.setText(tags.get(position).tag_label );
        txtDesc.setText(tags.get(position).tag_description);

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tags.remove(position);
                notifyDataSetChanged();
                Log.d("tag", "onClick: clicked" );

            }

        });


        return rowView;

    }

    @Override
    public void add(SemanticTag tag){
        tags.add(tag);
        notifyDataSetChanged();
    }
}
