package com.quiroga.shoppinglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class recipemenuactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipemenu);

        ArrayList<String> RecipeList = new ArrayList<String>();
        ListView listView = findViewById(R.id.RecipeList);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            String Title = (String) bundle.get("TitleStr");
            RecipeList.add(Title);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, RecipeList);
        listView.setAdapter(adapter);

        Button AddRecipes = (Button) findViewById(R.id.AddRecipe);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recipemenuactivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });

        }//onCreate

}
