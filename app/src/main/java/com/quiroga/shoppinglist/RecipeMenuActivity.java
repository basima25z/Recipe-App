package com.quiroga.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xmlpull.v1.XmlPullParser;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeMenuActivity extends AppCompatActivity {
    ArrayList<RecipeInfo> RecipeList = new ArrayList<>();
    ArrayList<String> RecipeTitles = new ArrayList<String>();
    private ListView listView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        // Make the activity's layout a child of the navigation drawer
        XmlPullParser activityMain = getResources().getLayout(R.layout.ingredients_activity);
        drawerLayout = findViewById(R.id.drawer_layout);
        getLayoutInflater().inflate(activityMain, drawerLayout);

        //recipe title array
        RecipeTitles.add(0,"Chicken Alfredo");
        //recipe data
        addRecipe("Chicken Alfredo","Chicken, Pasta, Alfredo Sauce","1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce");
        /*RecipeList.add(2,"Easy","Spaghetti");
        RecipeList.add(3,"Shrimp Scampi");
        RecipeList.add(4,"Ravioli");
        RecipeList.add(5,"Gnocchi");*/

        listView = findViewById(R.id.RecipeList);
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
                                startActivity(recipesIntent);                                break;
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
            String Title = (String) bundle.get("TitleStr");
            RecipeTitles.add(Title);
        }

        Gson gson = new Gson();
        final String Recipe = gson.toJson(RecipeList);

        //new recipe activity for clicked on recipes in the listview, unfinished
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                Intent intent = new Intent(RecipeMenuActivity.this, RecipeClickActivity.class);
                intent.putExtra("TitleStr", RecipeTitles);
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
