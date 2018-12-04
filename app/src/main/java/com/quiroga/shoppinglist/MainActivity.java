package com.quiroga.shoppinglist;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import android.app.AlertDialog;
import android.widget.EditText;
import android.content.DialogInterface;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Activity;
import java.lang.String;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import org.xmlpull.v1.XmlPullParser;


//Citation: Daniel Ross
//http://www.javacjava.com/ShoppingListOne.html

//Despite the name, this is the shopping list activity.
public class MainActivity extends AppCompatActivity {

    ArrayList<String> shoppingList = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    ListView listView = null;
    Button btnShare;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        // Make the activity's layout a child of the navigation drawer
        XmlPullParser activityMain = getResources().getLayout(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        getLayoutInflater().inflate(activityMain, drawerLayout);

        //shoppingList = getArrayValue(getApplicationContext());
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        final NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        navView.getMenu().findItem(R.id.nav_shopping_list).setChecked(false);
                        menuItem.setChecked(true);

                        switch(menuItem.getItemId()){
                            case R.id.nav_shopping_list :
                                // Do nothing
                                break;
                            case R.id.nav_ingredients:

                                Intent ingredientsIntent = new Intent(MainActivity.this, IngredientsActivity.class);
                                startActivity(ingredientsIntent);
                                break;
                            case R.id.nav_recipes:
                                Intent recipesIntent = new Intent(MainActivity.this, RecipeMenuActivity.class);
                                startActivity(recipesIntent);
                                break;
                        }

                        // close drawer when item is tapped
                        //drawerLayout.closeDrawers();

                        return true;
                    }
                });
        if(!navView.getMenu().findItem(R.id.nav_shopping_list).isChecked()){
            navView.getMenu().findItem(R.id.nav_shopping_list).setChecked(true);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String item = ((TextView) view).getText().toString();
                if (item.trim().equals(shoppingList.get(position).trim())) {
                    remove(item, position);
                } else {
                    Toast.makeText(getApplicationContext(),"Error Removing Element", Toast.LENGTH_LONG).show();
                }
            }
        });


        FloatingActionButton  fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add Item");
                final EditText input = new EditText(MainActivity.this);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shoppingList.add(upperCase(input.getText().toString()));
                        storeArrayValue(shoppingList, getApplicationContext());
                        listView.setAdapter(adapter);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });

        btnShare = findViewById(R.id.shareButton);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "",null));
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Shopping List");
                StringBuilder sb = new StringBuilder();
                for(String s : shoppingList)
                {
                    sb.append(s);
                    sb.append("\n");
                }
                shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                startActivity(Intent.createChooser(shareIntent, "Choose the app to send: " ));
            }
        });

        Button recipes = findViewById(R.id.SearchAddRecipes);
        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipeMenuActivity.class);
                startActivity(intent);
            }
        });

        Button recipeHistory = findViewById(R.id.RecipeHistoryButton);
        recipeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipeHistory.class);
                startActivity(intent);
            }
        });

        Button recipeSearch = findViewById(R.id.RecipeSearchButton);
        recipeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipeSearch.class);
                startActivity(intent);
            }
        });

        Button btnIngredients = findViewById(R.id.ingredientsButton);
        btnIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IngredientsActivity.class);
                startActivity(intent);            }
        });
    }//onCreate

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

    private String upperCase(String s) {
        if (s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }//upperCase

    public static void storeArrayValue(ArrayList<String> inArrayList, Context context) {
        Set<String> write = new HashSet<>(inArrayList);
        SharedPreferences wordSearch = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = wordSearch.edit();
        editor.putStringSet("myArray", write);
        editor.apply(); // Changed from editor.commit(); to get rid of an error
    }

    public static ArrayList getArrayValue( Context dan) {
        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues",Activity.MODE_PRIVATE);
        Set<String> tmp = new HashSet<>();
        tmp = WordSearchGetPrefs.getStringSet("myArray", tmp);
        return new ArrayList<>(tmp);
    }

    public void remove(String item, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + item + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shoppingList.remove(position);
                storeArrayValue(shoppingList, getApplicationContext());
                listView.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}//end of class