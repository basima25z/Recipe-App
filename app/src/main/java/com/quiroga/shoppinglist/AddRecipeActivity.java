package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {
    ArrayList<String> RecipeTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipelayout);

        Intent intent = getIntent();
        //RecipeTitles = getIntent().getStringArrayListExtra("RecipeTitles");
        //ArrayList<RecipeInfo> RecipeList = ("RecipeList");

        Button AddRecipes = findViewById(R.id.Add);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText RecipeTitle = findViewById(R.id.recipeTitle);//alan; takes edit texts and converts them to a string to send to recipe menu
                String RecipeTitleStr = RecipeTitle.getText().toString();
                EditText RecipeIndg = findViewById(R.id.recipeIndg);
                String RecipeIndgStr = RecipeIndg.getText().toString();
                EditText RecipeDir = findViewById(R.id.recipeDir);
                String RecipeDirStr = RecipeDir.getText().toString();

                Intent intent = new Intent(AddRecipeActivity.this, RecipeMenuActivity.class);
                intent.putExtra("RecipeTitleStr", RecipeTitleStr);
                intent.putExtra("RecipeIndgStr", RecipeIndgStr);
                intent.putExtra("RecipeDirStr", RecipeDirStr);
                startActivity(intent);
            }
        });
    }
}
