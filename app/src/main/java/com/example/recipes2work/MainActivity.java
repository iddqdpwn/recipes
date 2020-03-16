package com.example.recipes2work;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    String[] recipeIcon, recipeTitle;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        recipeIcon = getResources().getStringArray(R.array.iconName);
        recipeTitle = getResources().getStringArray(R.array.title);

        ListView listView = findViewById(R.id.listView);

        Intent myIntent = getIntent();
        adapter = new CustomAdapter(this,myIntent, recipeTitle, recipeIcon);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == 1) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener( new OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    generateDescriptionLayout(position);
                }
            } );
        } else {
            GridView gridView = findViewById(R.id.gridView);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   generateDescriptionLayout(position);
                }
            });
        }
    }
    public void generateDescriptionLayout(int position){
        Intent intent = new Intent(this, Description.class);
        intent.putExtra("recipeTitle", recipeTitle[position]);
        intent.putExtra("position", position);
        //startActivity(intent);
        startActivityForResult(intent,1);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            adapter.notifyDataSetChanged();
        }
    }
}
