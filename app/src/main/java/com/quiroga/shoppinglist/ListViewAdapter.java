package com.quiroga.shoppinglist;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class ListViewAdapter extends BaseAdapter
 {

     Context mContext;
    LayoutInflater inflater;
    private List<RecipeInfo> recipeInfoList = null;
    private ArrayList<RecipeInfo> arrayList;
    private ArrayList <String> filter_ing;
    private ArrayList<String> filter_title;
    private ArrayList<String> filter_dir;

    public ListViewAdapter(Context context, List<RecipeInfo> recipeInfoList){
        mContext = context;
        this.recipeInfoList = recipeInfoList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<RecipeInfo>();
        this.arrayList.addAll(recipeInfoList);
    }




     public class ViewHolder{
        TextView Title;
        TextView Ingredients;
        TextView Directions;
    }


    @Override
    public int getCount() {
        return recipeInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        Toast.makeText(mContext.getApplicationContext(), "reached", Toast.LENGTH_LONG).show();
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item,null);
            //locate the TextViews in listview_item.xml
            holder.Title = (TextView)view.findViewById(R.id.title);
            holder.Ingredients = (TextView)view.findViewById(R.id.ingredients);
            holder.Directions = (TextView)view.findViewById(R.id.directions);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)view.getTag();
        }
        //set the results into TextViews
        holder.Title.setText(recipeInfoList.get(position).getTitle());
        holder.Ingredients.setText(recipeInfoList.get(position).getIngredients());
        holder.Directions.setText(recipeInfoList.get(position).getDirections());

        //listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //send single item click data to SingleItemView Class
               Toast.makeText(mContext, "280 Calories", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }//end of getView

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        recipeInfoList.clear();
        if(charText.length()==0){
            recipeInfoList.addAll(arrayList);
        }
        else{
            for(RecipeInfo ri : arrayList){
                if(ri.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    recipeInfoList.add(ri);
                }
            }
        }
        notifyDataSetChanged();
    }


}
