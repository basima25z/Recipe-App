package com.quiroga.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.quiroga.shoppinglist.IngredientObject;
import com.quiroga.shoppinglist.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity {

    String[] data;
  //  List<IngredientObject> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   listData = new ArrayList<>();
        data = new String[]{"anddoille\n",
                "artichoke\n" ,
                "asparagus\n" ,
                "bacon\n" ,
                "barley\n" ,
                "bean sprout\n" ,
                "beef\n" ,
                "beet\n" ,
                "black bean\n" ,
                "black eyed peas\n" ,
                "bok choy\n" ,
                "bread crumb\n" ,
                "broccoli\n" ,
                "brown sugar\n" ,
                "butter\n" ,
                "butternut squash\n" ,
                "cabbage\n" ,
                "cannellini beans\n" ,
                "canola oil\n" ,
                "carrot\n" ,
                "cashew\n" ,
                "cauliflower\n",
                "celery\n" ,
                "cheddar\n" ,
                "chicken\n" ,
                "chive\n" ,
                "chorizo\n" ,
                "clams\n" ,
                "coconut milk\n" ,
                "corn\n" ,
                "cream\n" ,
                "cucumber\n" ,
                "egg noodles\n" ,
                "eggplant\n" ,
                "eggs\n" ,
                "endive\n" ,
                "flour\n" ,
                "garlic\n" ,
                "ginger\n" ,
                "green beans\n" ,
                "green chile\n" ,
                "green onion\n" ,
                "green pepper\n" ,
                "hickory ham\n" ,
                "hominy\n" ,
                "Italian sausage\n" ,
                "jalapeno\n" ,
                "kale\n" ,
                "kidney bean\n" ,
                "kielbasa\n" ,
                "lamb\n" ,
                "leek\n" ,
                "lemon\n" ,
                "lentils\n" ,
                "lily flower\n" ,
                "lime juice\n" ,
                "lobster\n" ,
                "mushroom\n" ,
                "okra\n" ,
                "olive oil\n" ,
                "onion\n" ,
                "pancetta\n" ,
                "parmesan\n" ,
                "parsley\n" ,
                "parsnip\n" ,
                "peanut butter\n" ,
                "peas\n" ,
                "pepper jack\n" ,
                "pine nuts\n" ,
                "pineapple\n" ,
                "pinto beans\n" ,
                "poblano\n" ,
                "pork\n" ,
                "potato\n" ,
                "pumpkin\n" ,
                "red beans\n" ,
                "red pepper\n" ,
                "red wine\n" ,
                "rice\n" ,
                "scallops\n" ,
                "secret herbs and spices\n" ,
                "shallot\n" ,
                "shiitake\n" ,
                "shrimp\n" ,
                "sour cream\n" ,
                "soy sauce\n" ,
                "spinach\n" ,
                "split peas\n" ,
                "sugar\n" ,
                "sweet potato\n" ,
                "tapioca flour\n" ,
                "tasso\n" ,
                "tomatillo\n" ,
                "tomato\n" ,
                "vinegar\n" ,
                "white beans\n" ,
                "wood ear mushroom\n" ,
                "yellow squash\n" ,
                "yogurt\n" ,
                "zucchini"};
        ListView listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(IngredientsActivity.this, data[position], Toast.LENGTH_SHORT).show();


            }
        });
    }
}
