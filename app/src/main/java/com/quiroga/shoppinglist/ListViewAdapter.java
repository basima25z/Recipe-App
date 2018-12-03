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


public class ListViewAdapter extends BaseAdapter //implements Filterable // implements filterable to get setTExtChange in RecipeSearch
 {

     Context mContext;
    LayoutInflater inflater;
    private List<RecipeInfo> recipeInfoList = null;
    private ArrayList<RecipeInfo> arrayList;
    private ArrayList <String> filter_values;

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
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item,null); //HAVENT DONE THIS YET
            //locate the TextViews in listview_item.xml
            holder.Title = (TextView)view.findViewById(R.id.title);
            holder.Ingredients = (TextView)view.findViewById(R.id.ingredients);//CHECK ON THIS
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
                Intent intent = new Intent(mContext, SingleItemView.class);
                //Pass all data title,ingredients, and directions
                intent.putExtra("Title:",(recipeInfoList.get(position).getTitle()));
                intent.putExtra("Ingredients:",(recipeInfoList.get(position).getIngredients()));
                intent.putExtra("Directions:",(recipeInfoList.get(position).getDirections()));

                //pass all data flag and start SingleItemView class
                mContext.startActivity(intent);
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

    public void ingredientsFilter()
    {
        IngredientsActivity IAobject = new IngredientsActivity(mContext.getApplicationContext()); //IngredientsActivity.getApplicationContext()
        filter_values = new ArrayList<>();
        for(int i=0;i<recipeInfoList.size();i++)
        {
            if(IngredientsActivity.getArrayValue(mContext.getApplicationContext()).get(i).equals(recipeInfoList.get(i).getIngredients()))
            {
                filter_values.add(recipeInfoList.get(i).getIngredients());
            }
            else if(IngredientsActivity.getArrayValue(mContext.getApplicationContext()).isEmpty())
            {
                Toast.makeText(mContext.getApplicationContext(), "Please add to your ingredients list", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(mContext.getApplicationContext(), "No matches found", Toast.LENGTH_LONG).show();

            }

        }
        notifyDataSetChanged();

    }
}
