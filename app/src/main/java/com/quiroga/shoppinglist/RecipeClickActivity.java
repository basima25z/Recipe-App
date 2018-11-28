package com.quiroga.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

public class RecipeClickActivity extends AppCompatActivity {
    String TitleStr = null;
    ArrayList<String> FavRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipeclicklayout);

        Intent intent = getIntent();
        //final ArrayList<String> FavRecipes = intent.getStringArrayListExtra("FavArray");
        final String RecipeTitle = intent.getStringExtra("TitleStr");
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

        final ToggleButton toggleButton = findViewById(R.id.myToggleButton);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.star));


        /*Button backbutton = findViewById(R.id.back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeClickActivity.this, RecipeMenuActivity.class);
                intent.putExtra("TitleStr", RecipeTitle);
                intent.putExtra("RecipeStr", Recipe);
                //if (FavRecipes.size() >= 1)
                    //intent.putExtra("FavArray", FavRecipes);
                startActivity(intent);
            }
        });*/
        }
    }


