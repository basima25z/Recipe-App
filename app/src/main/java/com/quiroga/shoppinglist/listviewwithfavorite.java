package com.quiroga.shoppinglist;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class listviewwithfavorite extends ListActivity {

    //ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewwithfavoriteact);

       // listView = findViewById(R.id.list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listviewwithfavoriteact,R.id.textView1,getResources().getStringArray(R.array.Strings));
        setListAdapter(adapter);
        ToggleButton toggleButton = findViewById(R.id.toggleButton1);
        toggleButton.setBackgroundResource(R.drawable.fillstar);

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item+" selected", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_list_view_with_toggle, menu);
        return true;
    }
}
