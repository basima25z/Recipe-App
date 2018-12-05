package com.quiroga.shoppinglist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class IngSearch extends Activity  {
    ListView listI;
    String[] Title;
    String[] Ingredients;
    String[] Directions;

    ArrayList<String> valuesIng = new ArrayList<>();
    //IngAdapter ingAdapter;
    ListViewAdapter ingAdapter;
    //ArrayList<RecipeInfo>


   // public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
       // Toast.makeText(getApplicationContext(), "reached", Toast.LENGTH_LONG).show();
        setContentView(R.layout.listview_ing);//lol yeah maybe ??? thinking dont need it bc not trying show before filter
        //View view = inflater.inflate(R.layout.ingsingleitemview, container, false);
        Title = new String[] {"Chicken Alfredo", "Pancakes", "Carne Asada Tacos"};
        Ingredients = new String[]{"Chicken, Pasta, Alfredo Sauce", "Pancake Mix, Water", "Flank Steak, Onions, Green Peppers"};
        Directions = new String[]{"1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce", "1)Mix water and pancake mix thoroughly\n2)Pour batter in hot oiled pan\n3)Cook 60 seconds one side, flip and cook 30 seconds", "1)Season steak with spices(not just salt and pepper)\n2)Chop up onions and peppers\n3)Grill Steak till desired color\n4)Saute onions and peppers\n5)Grill corn tortillas and stack steak and vegetables"};
        listI = (ListView)findViewById(R.id.inglistview);

        ArrayList<RecipeInfo> arrayList = new ArrayList<RecipeInfo>();

        for(int i = 0; i<Title.length; i++)
        {
            RecipeInfo ri = new RecipeInfo(Title[i], Ingredients[i], Directions[i]);
            arrayList.add(ri);
        }

       ingAdapter = new ListViewAdapter(this, arrayList);
     //  ingAdapter = new IngAdapter(this, arrayList);
       // listI = (ListView)findViewById(R.id.inglistview);
       // list = (ListView)findViewById(R.id.listview);

        //ingAdapter.ingredientsFilter();

        listI.setAdapter(ingAdapter);

       valuesIng= IngredientsActivity.getArrayValue(getApplicationContext());

        for(int i=0; i <valuesIng.size();i++)
        {
            ingAdapter.filter(valuesIng.get(i));
        }


        //ingAdapter.filter("chicken"); // typing
        //ingAdapter.filter("water");
       // ingAdapter.ingredientsFilter();
        //listI.setTextFilterEnabled(true);

        //
        //ingAdapter.filt();
        //ingAdapter.ingredientsFilter();


       // return view;


    }







}
