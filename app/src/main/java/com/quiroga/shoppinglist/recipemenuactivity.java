package com.quiroga.shoppinglist;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class RecipeInfo{
    String Title;
    String Ingredients;
    String Directions;
}

public class recipemenuactivity extends AppCompatActivity {
    ArrayList<RecipeInfo> RecipeList = new ArrayList<>();
    ArrayList<String> RecipeTitles = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipemenu);

        //recipe title array
        RecipeTitles.add("Chicken Alfredo");
        //recipe data
        addRecipe("Chicken Alfredo","Chicken, Pasta, Alfredo Sauce","1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce");
        /*RecipeList.add(2,"Easy","Spaghetti");
        RecipeList.add(3,"Shrimp Scampi");
        RecipeList.add(4,"Ravioli");
        RecipeList.add(5,"Gnocchi");*/

        listView = findViewById(R.id.RecipeList);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, RecipeTitles);
        listView.setAdapter(adapter);

        //retrieves the title string from recipe activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            String Title = (String) bundle.get("TitleStr");
            RecipeTitles.add(Title);
        }

        //new recipe activity for clicked on recipes in the listview, unfinished
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                Intent intent = new Intent(recipemenuactivity.this, RecipeActivity.class);
                intent.putExtra("TitleStr",RecipeList.get(0));
                startActivity(intent);
            }
        });*/

        Button AddRecipes = (Button) findViewById(R.id.AddRecipe);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recipemenuactivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });

        }//onCreate
    //recipe data array method
    public void addRecipe(String Title, String Ingredients, String Directions){
        RecipeInfo newRecipe = new RecipeInfo();
        newRecipe.Title = Title;
        newRecipe.Ingredients = Ingredients;
        newRecipe.Directions = Directions;
        RecipeList.add(newRecipe);
    }
}
