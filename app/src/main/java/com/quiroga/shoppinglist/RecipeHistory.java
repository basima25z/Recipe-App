package com.quiroga.shoppinglist;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import android.widget.Button; //BZ
import android.content.Intent; //BZ

public class RecipeHistory extends AppCompatActivity {
    ArrayList<String> recipeHistoryList = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    ListView lv = null;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recipeHistoryList = getArrayVal(getApplicationContext());
        //Collections.sort(recipeHistoryList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeHistoryList);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(recipeHistoryList.get(position).trim())) {
                    removeElement(selectedItem, position);
                } else {
                    Toast.makeText(getApplicationContext(), "Error Removing Element", Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RecipeHistory.this);
                builder.setTitle("Add Item");
                final EditText input = new EditText(RecipeHistory.this);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recipeHistoryList.add(preferredCase(input.getText().toString()));
                        storeArrayVal(recipeHistoryList, getApplicationContext());
                        lv.setAdapter(adapter);
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


        btnShare = (Button) findViewById(R.id.shareButton);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SENDTO, Uri.fromParts("mailto","",null));
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Recipe History List");
                StringBuilder sb = new StringBuilder();
                for(String s : recipeHistoryList)
                {
                    sb.append(s);
                    sb.append("\n");
                }
                shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                startActivity(shareIntent.createChooser(shareIntent, "Choose the app to send: " ));
            }
        });
    }//onCreate

    private String preferredCase(String s) {
        if (s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }//preferredCase

    public static void storeArrayVal( ArrayList<String> inArrayList, Context context) {
        Set<String> WhatToWrite = new HashSet<String>(inArrayList);
        SharedPreferences WordSearchPutPrefs = context.getSharedPreferences("dbArrayValues", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = WordSearchPutPrefs.edit();
        prefEditor.putStringSet("myArray", WhatToWrite);
        prefEditor.commit();
    }

    public static ArrayList getArrayVal( Context dan) {
        SharedPreferences WordSearchGetPrefs = dan.getSharedPreferences("dbArrayValues",Activity.MODE_PRIVATE);
        Set<String> tempSet = new HashSet<String>();
        tempSet = WordSearchGetPrefs.getStringSet("myArray", tempSet);
        return new ArrayList<String>(tempSet);
    }
    public void removeElement(String selectedItem, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recipeHistoryList.remove(position);
                Collections.sort(recipeHistoryList);
                storeArrayVal(recipeHistoryList, getApplicationContext());
                lv.setAdapter(adapter);
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