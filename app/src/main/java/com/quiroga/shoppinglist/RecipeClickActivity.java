package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class RecipeClickActivity extends AppCompatActivity {
    String TitleStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipeclicklayout);

        Intent intent = getIntent();
        String RecipeTitle = intent.getStringExtra("TitleStr");
        String Recipe = getIntent().getStringExtra("RecipeStr");

        Gson gson = new Gson();
        Type type = new TypeToken<List<RecipeInfo>>(){}.getType();
        List<RecipeInfo> RecipeList = gson.fromJson(Recipe, type);
        for (RecipeInfo info : RecipeList){
            String Title = info.Title;
            String Ingredients = info.Ingredients;
            String Directions = info.Directions;
            if (Title.equals(RecipeTitle)) {
                TextView title = (TextView) findViewById(R.id.title);
                title.setText(Title);
                TextView ingredients = (TextView) findViewById(R.id.ingredients);
                ingredients.setText(Ingredients);
                TextView directions = (TextView) findViewById(R.id.directions);
                directions.setText(Directions);
            }
        }
    }
}
