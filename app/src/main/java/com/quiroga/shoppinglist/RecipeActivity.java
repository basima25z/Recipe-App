package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipelayout);

        Button Recipes = (Button) findViewById(R.id.SearchAddRecipes);
        Recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(FirstScreen.this, SecondScreen.class);--
                //EditText Title = (EditText)findViewById(R.id.AddRecipe);
                //string
                //i.putExtra("STRING_I_NEED", strName);
                //Intent intent = new Intent(MainActivity.this, recipemenuactivity.class);
                //startActivity(intent);
            }
        });
        //save recipe and return to recipeMenu with the title of the new recipe and add it to the list
    }
}
