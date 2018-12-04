package com.quiroga.shoppinglist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class IngSearch extends Fragment
{
    String[] Title;
    String[] Ingredients;
    String[] Directions;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        Title = new String[] {"Chicken Alfredo", "Pancakes", "Carne Asada Tacos"};
        Ingredients = new String[]{"Chicken, Pasta, Alfredo Sauce", "Pancake Mix, Water", "Flank Steak, Onions, Green Peppers"};
        Directions = new String[]{"1) Boil water and cook pasta\n2)Saute chicken till well done\n3)Mix chicken, pasta and sauce", "1)Mix water and pancake mix thoroughly\n2)Pour batter in hot oiled pan\n3)Cook 60 seconds one side, flip and cook 30 seconds", "1)Season steak with spices(not just salt and pepper)\n2)Chop up onions and peppers\n3)Grill Steak till desired color\n4)Saute onions and peppers\n5)Grill corn tortillas and stack steak and vegetables"};

        ArrayList<RecipeInfo> arrayList = new ArrayList<RecipeInfo>();

        for(int i = 0; i<Title.length; i++)
        {
            RecipeInfo ri = new RecipeInfo(Title[i], Ingredients[i], Directions[i]);
            arrayList.add(ri);
        }



    }







}
