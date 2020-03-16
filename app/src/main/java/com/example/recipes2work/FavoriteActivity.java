package com.example.recipes2work;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    String[] favoriteTitles, favoriteImgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //custom toolbar initialization
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.favorite));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        loadFavorites();
        ListView listView = findViewById(R.id.listView);
        Intent myIntent = getIntent();
        CustomAdapter adapter = new CustomAdapter(this, myIntent, favoriteTitles, favoriteImgUrl);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                genLayout(position);
            }
        });
        super.onStart();
    }

    public void genLayout(int position) {
        Intent intent = new Intent(this, Description.class);
        intent.putExtra("recipeTitle", favoriteTitles[position]);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void loadFavorites() {
        String[] name = getResources().getStringArray(R.array.title);
        String[] imgUrl = getResources().getStringArray(R.array.iconName);
        ArrayList<String> recipeTitle = new ArrayList<>();
        ArrayList<String> recipeImg = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            SharedPreferences sPref = getSharedPreferences("Favorite", MODE_PRIVATE);
            String value = sPref.getString(name[i], "not");
            if (!value.equals("not")) {
                recipeTitle.add(name[i]);
                recipeImg.add(imgUrl[i]);
            }
        }
        favoriteTitles = new String[recipeTitle.size()];
        favoriteImgUrl = new String[recipeImg.size()];
        for (int i = 0; i < recipeTitle.size(); i++) {
            favoriteTitles[i] = recipeTitle.get(i);
            favoriteImgUrl[i] = recipeImg.get(i);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
