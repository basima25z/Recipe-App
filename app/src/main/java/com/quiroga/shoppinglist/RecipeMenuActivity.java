package com.quiroga.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecipeMenuActivity extends AppCompatActivity // do we extend fragment here???
 {
    ArrayList<RecipeInfo> RecipeList = new ArrayList<>();
    ArrayList<String> RecipeTitles = new ArrayList<>();
    ArrayList<String> FavRecipes = new ArrayList<>();
    Gson gson = new Gson();
    String Recipe = "0";
    ListView listView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        // Make the activity's layout a child of the navigation drawer
        XmlPullParser activityMain = getResources().getLayout(R.layout.recipemenu);
        drawerLayout = findViewById(R.id.drawer_layout);
        getLayoutInflater().inflate(activityMain, drawerLayout);

        //recipe title/Data array
        RecipeTitles.add(0,"Chicken Alfredo");
        addRecipe("Chicken Alfredo","Chicken, Pasta, Alfredo Sauce","1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce");
        RecipeTitles.add(1,"Pancakes");
        addRecipe("Pancakes","Pancake Mix, Water","1)Mix water and pancake mix thoroughly\n2)Pour batter in hot oiled pan\n3)Cook 60 seconds one side, flip and cook 30 seconds");
        RecipeTitles.add(2,"Carne Asada Tacos");
        addRecipe("Carne Asada Tacos","Flank Steak, Onions, Green Peppers","1)Season steak with spices(not just salt and pepper)\n2)Chop up onions and peppers\n3)Grill Steak till desired color\n4)Saute onions and peppers\n5)Grill corn tortillas and stack steak and vegetables");
        RecipeTitles.add(3,"Cheese Burger");
        addRecipe("Cheese Burger","Ground beef, Burger buns, cheese, lettuce, tomato, onions, pickles ","1) Season beef with salt and pepper\n2)Take a handful of beef and smash it onto a hot skillet, cook for 2-3 minutes\n3)chop up topping\n4)Flip Burger, add cheese, and cook another 2 minutes\n5)Brush inside of buns with butter and toast on skillet\n6)Assemble burger and Enjoy");
        RecipeTitles.add(4,"Fried Rice");
        addRecipe("Fried Rice","Rice, eggs, Mixed vegetables, Sesame oil, Onions, Soy sauce","1)Use rice cooker to rice at a 1|1.5 ratio of rice and water\n2)Heat oil in skillet and saute onions and vegetables\n3)Scramble 2 eggs separately from vegetables\n4)Mix in rice, add soy sauce, and Enjoy");

        //creates listview
        listView = findViewById(R.id.lv);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, RecipeTitles);
        listView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();



                        switch(menuItem.getItemId()){
                            case R.id.nav_shopping_list :
                                Intent ingredientsIntent = new Intent(RecipeMenuActivity.this, MainActivity.class);
                                startActivity(ingredientsIntent);
                                break;
                            case R.id.nav_ingredients:
                                Intent recipesIntent = new Intent(RecipeMenuActivity.this, IngredientsActivity.class);
                                startActivity(recipesIntent);
                                break;
                            case R.id.nav_recipes:
                                // Do nothing
                                break;
                        }

                        return true;
                    }
                });

        //retrieves the title string from recipe activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            //gets strings from addrecipeactivity
            String RecipeTitleStr  = (String) bundle.get("RecipeTitleStr");
            String RecipeIndgStr  = (String) bundle.get("RecipeIndgStr");
            String RecipeDirStr  = (String) bundle.get("RecipeDirStr");
            addRecipe(RecipeTitleStr, RecipeIndgStr, RecipeDirStr);//adds new recipe to RecipeList array
            RecipeTitles.add(RecipeTitleStr);
        }

        Recipe = gson.toJson(RecipeList);
        //new recipe activity for clicked on recipes in the listview//sends whole RecipeList to click activity and title for clicked recipe
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {

                Intent intent = new Intent(RecipeMenuActivity.this, RecipeClickActivity.class);
                intent.putExtra("TitleStr", RecipeTitles.get(position));
                intent.putExtra("RecipeStr", Recipe);
                //intent.putExtra("FavArray", FavRecipes); // sends array to clicked recipe for fav button
                startActivity(intent);
            }
        });

        SharedPreferences sharedPrefs = getSharedPreferences("togglestatus", MODE_PRIVATE);

        Button AddRecipes = findViewById(R.id.AddRecipe);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeMenuActivity.this, AddRecipeActivity.class);
                //intent.putStringArrayListExtra("RecipeTitles", RecipeTitles);
                //intent.putExtra("Recipe", RecipeList);
                startActivity(intent);
            }
        });

        final Button FavRecipes = findViewById(R.id.FavRec);
        FavRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gson gson = new Gson();
                //final String FavRecipeStr = gson.toJson(FavRecipes);

                Intent intent = new Intent(RecipeMenuActivity.this, listviewwithfavorite.class);
                //if (FavRecipeStr != null)
                    //intent.putExtra("FavRecipesMenu", FavRecipeStr); // sends array to display titles
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

    // Opens the drawer when the hamburger button in the toolbar is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}




