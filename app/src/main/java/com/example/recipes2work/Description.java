package com.example.recipes2work;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

public class Description extends AppCompatActivity {

    private Toolbar mToolbar;
    String title;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //custom toolbar initialization
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title = getIntent().getExtras().getString("recipeTitle");
        getSupportActionBar().setTitle(title);



        ImageView topImage = findViewById(R.id.imageViewTop);
        String[] iconName=getResources().getStringArray(R.array.iconName);
        int pos = getIntent().getExtras().getInt("position");
        Picasso.with(this).load("file:///android_asset/icon/"+iconName[pos]).into(topImage);

        TextView textDescription = findViewById(R.id.textDescription);
        String descriptionText=load("description/opis"+pos+".txt");
        textDescription.setText(descriptionText);

        TextView textIngredients = findViewById(R.id.textIngredients);
        String ingredients=load("ing/ing"+pos+".txt");
        textIngredients.setText(ingredients);

        WebView webView = findViewById(R.id.webView1);
        webView.loadUrl("file:///android_asset/html/ht"+pos+".html");
        webView.setBackgroundColor(Color.TRANSPARENT);

    }
    public String load (String text){
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(text);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str_data = new String(buffer);
        return str_data;
    }

    private boolean isFavorite() {
        SharedPreferences sPref = getSharedPreferences("Favorite", MODE_PRIVATE);
        String fav = sPref.getString(title, "not");
        if (fav.equals("not")) {
            return false;
        }
        return true;
    }

    private void addToFavorite() {
        SharedPreferences sPref = getSharedPreferences("Favorite", MODE_PRIVATE);
        Editor ed = sPref.edit();
        String value = "" + position;
        ed.putString(title, value);
        ed.commit();
    }

    private void deleteFromFavorite() {
        SharedPreferences sPref = getSharedPreferences("Favorite", MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(title, "not");
        ed.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_description, menu);
        MenuItem menuItem = menu.findItem(R.id.favorites);
        if (isFavorite()) {
            menuItem.setIcon(R.drawable.ic_favorite_24_px);
        } else {
            menuItem.setIcon(R.drawable.favorite);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                this.finish();
                return true;
            case R.id.favorites:
                if (!isFavorite()) {
                    item.setIcon(R.drawable.ic_favorite_24_px);
                    addToFavorite();
                } else {
                    item.setIcon(R.drawable.favorite);
                    deleteFromFavorite();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

