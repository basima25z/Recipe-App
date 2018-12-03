package com.quiroga.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class SingleItemView extends Activity
{
    TextView txtTitle;
    TextView txtIngredients;
    TextView txtDirections;

    String Title;
    String Ingredients;
    String Directions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        //retrieves data from RecipeSearch on item click event
        Intent i = getIntent();
        //get the results of title, ingredients, directions
        Title = i.getStringExtra("Title");
        Ingredients = i.getStringExtra("Ingredients");
        Directions = i.getStringExtra("Directions");

        //locate the TextViews in singleitemview.xml
        txtTitle = (TextView)findViewById(R.id.title);
        txtIngredients = (TextView)findViewById(R.id.ingredients);
        txtDirections = (TextView)findViewById(R.id.directions);

        //load the results into the TextViews
        txtTitle.setText(Title);
        txtIngredients.setText(Ingredients);
        txtDirections.setText(Directions);

    }
}
