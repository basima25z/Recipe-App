package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {
    String TitleStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipelayout);

        Button AddRecipes = (Button) findViewById(R.id.Add);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText RecipeTitle = (EditText) findViewById(R.id.recipeTitle);
                String RecipeTitleStr = RecipeTitle.getText().toString();
                Intent intent = new Intent(RecipeActivity.this, recipemenuactivity.class);
                intent.putExtra("TitleStr", RecipeTitleStr );
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Recipe Added!", Toast.LENGTH_LONG).show();
            }
        });
        //save recipe and return to recipeMenu with the title of the new recipe and add it to the list
    }
}
