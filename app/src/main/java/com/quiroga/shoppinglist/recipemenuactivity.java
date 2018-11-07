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
    ArrayList<String> recipeList = null;
    ArrayAdapter<String> adapter = null;
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipemenu);

        Button AddRecipes = (Button) findViewById(R.id.AddRecipe);
        AddRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recipemenuactivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });

        recipeList = getArrayValue(getApplicationContext());
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeList);
        listView =  findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String item = ((TextView) view).getText().toString();
                if (item.trim().equals(recipeList.get(position).trim())) {
                    remove(item, position);
                } else {
                    Toast.makeText(getApplicationContext(),"Error Removing Element", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void goToIngredientsActivity(){
        Intent intent = new Intent(this, IngredientsActivity.class);
        startActivity(intent);
    }

    private String upperCase(String s) {
        if (s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }//upperCase


    public static void storeArrayValue( ArrayList<String> inArrayList, Context context) {
        Set<String> write = new HashSet<String>(inArrayList);
        SharedPreferences wordSearch = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = wordSearch.edit();
        editor.putStringSet("myArray", write);
        editor.commit();
    }

    public static ArrayList getArrayValue( Context dan) {
        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues",Activity.MODE_PRIVATE);
        Set<String> tmp = new HashSet<String>();
        tmp = WordSearchGetPrefs.getStringSet("myArray", tmp);
        return new ArrayList<String>(tmp);
    }
    public void remove(String item, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + item + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recipeList.remove(position);
                storeArrayValue(recipeList, getApplicationContext());
                listView.setAdapter(adapter);
            }
        });
}
}
