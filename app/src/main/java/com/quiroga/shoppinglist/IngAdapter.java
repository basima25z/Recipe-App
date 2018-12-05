package com.quiroga.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
 import java.util.List;
 import java.net.ConnectException;
import java.util.ArrayList;
    public class IngAdapter extends BaseAdapter//ArrayAdapter<RecipeInfo>
    {
        private Context context;
        private ArrayList<String> filter_ing;
        private ArrayList<String> filter_title;
        private ArrayList<String> filter_dir;
        private List <RecipeInfo> arrayListPassed = null;
        private ArrayList<RecipeInfo> arrayList;
        LayoutInflater inflater;
        public IngAdapter(Context context, List <RecipeInfo> arrayListPassed)
        {
            //super(context,0,recipeInfoArrayList);
            this.context=context;
            this.arrayListPassed = arrayListPassed;
            filter_title = new ArrayList<String>();
            filter_ing = new ArrayList<String>();
            filter_dir = new ArrayList<String>();
            inflater = LayoutInflater.from(context);
            // this.arrayList = new ArrayList<RecipeInfo>();
            // this.arrayList.addAll(arrayListPassed); // this gets arrayList as an object
            // ingredientsFilter();
            //  filt();
        }
        public class ViewHolder{
            TextView Title;
            TextView Ingredients;
            TextView Directions;
        }
        @Override
        public int getCount()
        {
            return arrayListPassed.size();
        }
        @Override
        public Object getItem(int position)
        {
            return arrayListPassed.get(position);
        }
        @Override
        public long getItemId(int position)
        {
            return position;
        }
        public void filt()
        {
            Toast.makeText(context.getApplicationContext(), "reached", Toast.LENGTH_LONG).show();
            //ingredientsFilter();
        }
        public void ingredientsFilter()
        {
            Toast.makeText(context.getApplicationContext(), "reached", Toast.LENGTH_LONG).show();
            IngredientsActivity IAobject = new IngredientsActivity(context.getApplicationContext()); //IngredientsActivity.getApplicationContext()
            filter_ing = new ArrayList<>();
            for(int i=0;i<arrayListPassed.size();i++)
            {
                if(IngredientsActivity.getArrayValue(context.getApplicationContext()).get(i).equals(arrayListPassed.get(i).getIngredients()))
                {
                    filter_ing.add(arrayListPassed.get(i).getIngredients());
                    filter_dir.add(arrayListPassed.get(i).getDirections());
                    filter_title.add(arrayListPassed.get(i).getTitle());
                }
                else if(IngredientsActivity.getArrayValue(context.getApplicationContext()).isEmpty())
                {
                    Toast.makeText(context.getApplicationContext(), "Please add to your ingredients list", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(context.getApplicationContext(), "No matches found", Toast.LENGTH_LONG).show();
                }
            }
            notifyDataSetChanged();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            //ingredientsFilter();
            final ViewHolder hold;
            //if(convertView==null)
            // {
            hold = new ViewHolder();
            convertView=inflater.inflate(R.layout.listview_ing,null);
            hold.Title = (TextView)convertView.findViewById(R.id.title);
            hold.Ingredients = (TextView)convertView.findViewById(R.id.ingredients);//CHECK ON THIS
            hold.Directions = (TextView)convertView.findViewById(R.id.directions);
            convertView.setTag(hold);
            // }
            //  else
            // {         hold = (ViewHolder)convertView.getTag();
            //}
            hold.Title.setText(filter_title.get(position));
//        hold.Title.setText("lol");
            hold.Ingredients.setText(filter_ing.get(position));
            hold.Directions.setText(filter_dir.get(position));
            //hold.Title.setText(recipeInfoList.get(position).getTitle());
//        hold.Ingredients.setText(recipeInfoList.get(position).getIngredients());
//        hold.Directions.setText(recipeInfoList.get(position).getDirections());
            Toast.makeText(context.getApplicationContext(), "lol", Toast.LENGTH_LONG).show();
            return convertView;
        }
    }