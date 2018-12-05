package com.quiroga.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    public static final Boolean togglestatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipeclicklayout);

        final ToggleButton toggleButton = findViewById(R.id.myToggleButton);
        SharedPreferences sharedPrefs = getSharedPreferences("togglestatus", MODE_PRIVATE);
        toggleButton.setChecked(sharedPrefs.getBoolean("status", true));

        Intent intent = getIntent();
        //final ArrayList<String> FavRecipes = intent.getStringArrayListExtra("FavArray");
        final String RecipeTitle = intent.getStringExtra("TitleStr");
        String Recipe = getIntent().getStringExtra("RecipeStr");

        //alan; code u info sent from recipe menu to a string and then parses through it
        //uses recipe info variables to assign each string to its appropriate variable then compares the clicked recipe title and the parsed array title to display right info
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

        //toggleButton.setBackgroundResource(R.drawable.star);
        toggleButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            {
                if (toggleButton.isChecked()) {
                    toggleButton.setBackgroundResource(R.drawable.fillstar);
                    SharedPreferences.Editor editor = getSharedPreferences("togglestatus", MODE_PRIVATE).edit();
                    editor.putBoolean("status", true).commit();
                } else {
                    toggleButton.setBackgroundResource(R.drawable.star);
                    SharedPreferences.Editor editor = getSharedPreferences("togglestatus", MODE_PRIVATE).edit();
                    editor.putBoolean("status", false).commit();
                }
            }
        }
        });
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


