package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipelayout);

        Button AddRecipes = (Button) findViewById(R.id.Add);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText Title = (EditText)findViewById(R.id.AddRecipe);
                //string str =
                Intent intent = new Intent(RecipeActivity.this, recipemenuactivity.class);
                Toast.makeText(getApplicationContext(),"Recipe Added!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        //save recipe and return to recipeMenu with the title of the new recipe and add it to the list
    }
}
