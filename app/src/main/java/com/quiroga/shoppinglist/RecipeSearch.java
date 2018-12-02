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
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class RecipeSearch extends Activity {

    ListView list;
    ListViewAdapter adapter;//HAVENT DONE THIS YET
    EditText editsearch;
    String[] Title;
    String[] Ingredients;
    String[] Directions;

    ArrayList<RecipeInfo> arrayList = new ArrayList<RecipeInfo>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.listview_main);//HAVENT DONE THIS YET

        Title = new String[] {"Chicken Alfredo", "Pancakes", "Carne Asada Tacos"};
        Ingredients = new String[]{"Chicken, Pasta, Alfredo Sauce", "Pancake Mix, Water", "Flank Steak, Onions, Green Peppers"};
        Directions = new String[]{"1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce", "1)Mix water and pancake mix thoroughly\n2)Pour batter in hot oiled pan\n3)Cook 60 seconds one side, flip and cook 30 seconds", "1)Season steak with spices(not just salt and pepper)\n2)Chop up onions and peppers\n3)Grill Steak till desired color\n4)Saute onions and peppers\n5)Grill corn tortillas and stack steak and vegetables"};

        //locate the ListView in the listview_main.xml
        list = (ListView)findViewById(R.id.listview);//HAVENT DONE THIS YET


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

    }//end of OnCreate

    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);//Don't know how to fix

        return true;
    }//end of onCreateOptionsMenu
}//end of class
