package com.quiroga.shoppinglist;


import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;
import android.content.Intent;

public class RecipeSearch extends Activity {

    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    String[] Title;
    String[] Ingredients;
    String[] Directions;
    //ToggleButton t; // bas
    Button filter;
    Button request;
    int i=1;

    ArrayList<RecipeInfo> arrayList = new ArrayList<RecipeInfo>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        Title = new String[] {"Chicken Alfredo", "Pancakes", "Carne Asada Tacos"};
        Ingredients = new String[]{"Chicken, Pasta, Alfredo Sauce", "Pancake Mix, Water", "Flank Steak, Onions, Green Peppers"};
        Directions = new String[]{"1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce", "1)Mix water and pancake mix thoroughly\n2)Pour batter in hot oiled pan\n3)Cook 60 seconds one side, flip and cook 30 seconds", "1)Season steak with spices(not just salt and pepper)\n2)Chop up onions and peppers\n3)Grill Steak till desired color\n4)Saute onions and peppers\n5)Grill corn tortillas and stack steak and vegetables"};

        //locate the ListView in the listview_main.xml
        list = (ListView)findViewById(R.id.listview);


        for(int i = 0; i<Title.length; i++)
        {
            RecipeInfo ri = new RecipeInfo(Title[i], Ingredients[i], Directions[i]);
            arrayList.add(ri);
        }

        //pass the results to the ListViewAdapter Class
        adapter = new ListViewAdapter(this, arrayList);

        //binds the adapter to the ListView
        list.setAdapter(adapter);


        //locate the editText in listview_main.xml
        editsearch = (EditText)findViewById(R.id.search);

        //capture text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            @Override
            public void afterTextChanged(Editable s) {
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);

            }
        });


        filter =(Button)findViewById(R.id.filterbutton);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                    Intent nextScreen = new Intent (RecipeSearch.this, IngSearch.class);
                    startActivity(nextScreen);

            }
        });

        request = (Button)findViewById(R.id.request);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent nextScreen = new Intent (RecipeSearch.this, Request.class);
                startActivity(nextScreen);

            }
        });













    }//end of OnCreate
              //  onCreateView(inflator, container);????

// onCreateView is never called? I think this is bc you have to pass it some shit

//        public View OnCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
//        {
//            View view = inflater.inflate(R.layout.listview_main,container, false);
//            t=(ToggleButton) findViewById(R.id.toggleButton);
//            //return inflator.inflate(R.layout.listview_main, container, false);
//
//            t.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view)
//                {
//                    boolean on = ((ToggleButton)view).isChecked();
//
//                    if(on)
//                    {
//                        adapter.ingredientsFilter(); // this will be created in the adapter class
//                        t.setTextOff("No Filter");
//                        t.setChecked(true);
//                    }
//                    else
//                    {
//                        t.setTextOn("Filter");
//                        t.setChecked(false);
//                    }
//                }
//            });
//            return view;
//
//        }

    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);//Don't know how to fix

        return true;
    }//end of onCreateOptionsMenu
}//end of class
