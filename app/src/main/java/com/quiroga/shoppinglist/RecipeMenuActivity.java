package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeMenuActivity extends AppCompatActivity {
    ArrayList<RecipeInfo> RecipeList = new ArrayList<>();
    ArrayList<String> RecipeTitles = new ArrayList<String>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipemenu);

        //recipe title/Data array
        RecipeTitles.add(0,"Chicken Alfredo");
        addRecipe("Chicken Alfredo","Chicken, Pasta, Alfredo Sauce","1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce");
        RecipeTitles.add(1,"Pancakes");
        addRecipe("Pancakes","Pancake Mix, Water","1)Mix water and pancake mix thoroughly\n2)Pour batter in hot oiled pan\n3)Cook 60 seconds one side, flip and cook 30 second");
        RecipeTitles.add(2,"Carne Asada Tacos");
        addRecipe("Carne Asada Tacos","Flank Steak, Onions, Green Peppers","1)Season steak with spices(not just salt and pepper)\n2)Chop up onions and peppers\n3)Grill Steak till desired color\n4)Saute onions and peppers\n5)Grill corn tortillas and stack steak and vegetables");
        RecipeTitles.add(3,"Cheese Burger");
        addRecipe("Cheese Burger","Ground beef, Burger buns, cheese, lettuce, tomato, onions, pickles ","1) Season beef with salt and pepper\n2)Take a handful of beef and smash it onto a hot skillet, cook for 2-3 minutes\n3)chop up topping\n4)Flip Burger, add cheese, and cook another 2 minutes\n5)Brush inside of buns with butter and toast on skillet\n6)Assemble burger and Enjoy");
        RecipeTitles.add(4,"Fried Rice");
        addRecipe("Fried Rice","Rice, eggs, Mixed vegetables, Sesame oil, Onions, Soy sauce","1)Use rice cooker to rice at a 1|1.5 ratio of rice and water\n2)Heat oil in skillet and saute onions and vegetables\n3)Scramble 2 eggs separately from vegetables\n4)Mix in rice, add soy sauce, and Enjoy");

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

        Gson gson = new Gson();
        final String Recipe = gson.toJson(RecipeList);

        //new recipe activity for clicked on recipes in the listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                Intent intent = new Intent(RecipeMenuActivity.this, RecipeClickActivity.class);
                intent.putExtra("TitleStr", RecipeTitles.get(position));//test
                intent.putExtra("RecipeStr", Recipe);
                startActivity(intent);
            }
        });

        Button AddRecipes = (Button) findViewById(R.id.AddRecipe);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeMenuActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });

        }//onCreate
    //recipe data array method
    public RecipeInfo addRecipe(String Title, String Ingredients, String Directions){
        RecipeInfo newRecipe = new RecipeInfo();
        newRecipe.Title = Title;
        newRecipe.Ingredients = Ingredients;
        newRecipe.Directions = Directions;
        RecipeList.add(newRecipe);
        return newRecipe;
    }
}
