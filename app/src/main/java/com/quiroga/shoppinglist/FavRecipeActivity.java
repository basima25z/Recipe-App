package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavRecipeActivity extends AppCompatActivity {
    ArrayList<String> FavRecipes = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewwithfavoriteact);

        Intent intent = getIntent();
        final String FaveRecipes = intent.getStringExtra("FavRecipesMenu");

        Gson gson = new Gson();
        Type type = new TypeToken<List<RecipeInfo>>(){}.getType();
        List<RecipeInfo> FavRecList = gson.fromJson(FaveRecipes, type);

        listView = findViewById(R.id.FavList);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, FavRecList);
        listView.setAdapter(adapter);
    }
}