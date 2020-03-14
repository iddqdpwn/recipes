package com.example.recipes2work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    String[] imageUrl;
    ImageView imageView;
    View rowView;

    public CustomAdapter(Context context, String[] values, String[] imageUrl) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
        this.imageUrl = imageUrl;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = rowView.findViewById(R.id.text_list_item);
        textView.setText(values[position]);
        imageView = rowView.findViewById(R.id.image_list_item);
        Picasso.with(context).load("file:///android_asset/icon_small/" + imageUrl[position]).into(imageView);
        return rowView;
    }
}
