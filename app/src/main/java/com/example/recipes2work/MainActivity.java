package com.example.recipes2work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    String[] recipeIcon, recipeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //custom toolbar initialization
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        recipeIcon = getResources().getStringArray(R.array.iconName);
        recipeTitle = getResources().getStringArray(R.array.title);
        ListView listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, recipeTitle, recipeIcon);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == 1) {
            listView.setAdapter(adapter);
        } else {
            GridView gridView = findViewById(R.id.gridView);
            gridView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
        return false;
    }


}