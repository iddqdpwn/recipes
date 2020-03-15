package com.example.recipes2work;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    RelativeLayout myline;//BLAYD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //custom toolbar initialization
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title = getIntent().getExtras().getString("recipeTitle");
        getSupportActionBar().setTitle(title);

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

