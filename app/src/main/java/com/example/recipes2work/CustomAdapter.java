package com.example.recipes2work;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private Intent intent;
    private String[] values;
    String[] imageUrl;
    ImageView imageView;
    View rowView;
    String title, isFavorite;


    public CustomAdapter(Context context, Intent intent, String[] values, String[] imageUrl) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
        this.imageUrl = imageUrl;
        this.intent = intent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        title = values[position];
        SharedPreferences sPref = getContext().getSharedPreferences("Favorite", MODE_PRIVATE);
        isFavorite = sPref.getString(title, "not");
        if (isFavorite.equals("not")) {
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        } else {
            rowView = inflater.inflate(R.layout.list_item_favorite, parent, false);
        }
        TextView textView = rowView.findViewById(R.id.text_list_item);
        textView.setText(values[position]);
        imageView = rowView.findViewById(R.id.image_list_item);
        Picasso.with(context).load("file:///android_asset/icon_small/" + imageUrl[position]).into(imageView);
        return rowView;
    }
}
