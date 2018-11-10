package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRecipeActivity extends AppCompatActivity {
    String TitleStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipelayout);

        //ArrayList<RecipeInfo> RecipeList = (ArrayList<RecipeInfo>) getIntent().getSerializableExtra("mylist");
        //ArrayList<String> RecipeTitles = (ArrayList<String>) getIntent().getSerializableExtra("TitleStr");
        //Toast.makeText(getApplicationContext(),RecipeTitles.get(0), Toast.LENGTH_LONG).show();

        Button AddRecipes = (Button) findViewById(R.id.Add);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText RecipeTitle = (EditText) findViewById(R.id.recipeTitle);
                //String RecipeTitleStr = RecipeTitle.getText().toString();
                Intent intent = new Intent(AddRecipeActivity.this, RecipeMenuActivity.class);
                //intent.putExtra("TitleStr", RecipeTitleStr );
                startActivity(intent);
            }
        });
    }
}
