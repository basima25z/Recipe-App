package com.quiroga.shoppinglist;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


//Citation: Daniel Ross
//http://www.javacjava.com/ShoppingListOne.html
public class MainActivity extends AppCompatActivity {

    ArrayList<String> shoppingList = null;
    ArrayAdapter<String> adapter = null;
    ListView listView = null;
    Button btnShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoppingList = getArrayValue(getApplicationContext());
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingList);
        listView =  findViewById(R.id.listView);
        listView.setAdapter(adapter);


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

        btnShare = (Button) findViewById(R.id.shareButton);
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
                startActivity(shareIntent.createChooser(shareIntent, "Choose the app to send: " ));
            }
        });
    }//onCreate



    



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