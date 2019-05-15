package com.example.foodtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseDishActivity extends AppCompatActivity {
    String choosenCountry;
    ArrayList<Dish> dishesForCurrentCountry;
    ListView lvDishes;
    private  DishAdapter dishAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dish);

        choosenCountry = getIntent().getStringExtra("CountrySearchResult");
        TextView txvCountry = findViewById(R.id.txvCountry);
        txvCountry.setText(choosenCountry);

        int countryIndex = 0;
        try {
            countryIndex = Arrays.asList(getResources().getStringArray(R.array.listOfCountries)).indexOf(choosenCountry);
        }
        catch (Exception e)
        {

        }
        lvDishes = findViewById(R.id.lvDishes);

       dishesForCurrentCountry = sortDishes(countryIndex, DishCollection.getDishes());

        dishAdapter = new DishAdapter(
                getApplicationContext(),
                R.layout.row_layout,
                dishesForCurrentCountry
        );

        lvDishes.setAdapter(dishAdapter);

       /* lvDishes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailedDishInfoActivity.class);
                //intent.putExtra("ChooseDishResult", position);
                startActivity(intent);
            }
        });*/
    }

    private ArrayList<Dish> sortDishes(int countryCode, ArrayList<Dish> dishes) {
        ArrayList <Dish> result = new ArrayList<>();

        for (Dish d: dishes
             ) {
            if(d.getCountyCode() == countryCode)
                result.add(d);
        }

        return result;
    }

    public class DishAdapter extends ArrayAdapter {

        private List<Dish> dishes;
        private int resource;
        private LayoutInflater inflater;

        public DishAdapter(Context context, int resource, List<Dish> objects) {
            super(context, resource, objects);
            dishes = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            try {
                Dish currentDish = dishes.get(position);
                if (convertView == null) {
                    convertView = inflater.inflate(resource, null);
                }
                TextView tvDishName = convertView.findViewById(R.id.tvDishName);
                TextView tvDescription = convertView.findViewById(R.id.tvDescription);
                RatingBar rbRating = convertView.findViewById(R.id.rbRating);
                TextView tvOther = convertView.findViewById(R.id.tvOther);
                TextView tvId = convertView.findViewById(R.id.tvId);

                tvDishName.setText(currentDish.getName());
                tvDescription.setText(currentDish.getDescription());
                rbRating.setRating(currentDish.getRating());
                tvOther.setText(currentDish.getOther());
                int i = currentDish.getDishId();
                tvId.setText(String.valueOf(i));




               /* ivPicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // TextView tvDishId = v.findViewById(R.id.tvId);
                        Intent intent = new Intent(getApplicationContext(), DetailedDishInfoActivity.class);
                      //  intent.putExtra("ChooseDishResult", tvDishId.getText());
                        startActivity(intent);
                    }
                });*/
                tvDishName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tvDishName = v.findViewById(R.id.tvDishName);
                        String dishName = tvDishName.getText().toString();

                        Intent intent = new Intent(getApplicationContext(), DetailedDishInfoActivity.class);
                        intent.putExtra("ChooseDishResult", String.valueOf(getDishIdFromName(dishes, dishName)));
                        startActivity(intent);
                    }
                });
               /* tvDescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // TextView tvDishId = v.findViewById(R.id.tvId);
                        Intent intent = new Intent(getApplicationContext(), DetailedDishInfoActivity.class);
                       // intent.putExtra("ChooseDishResult", tvDishId.getText());
                        startActivity(intent);
                    }
                });
                tvOther.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  TextView tvDishId = v.findViewById(R.id.tvId);
                        Intent intent = new Intent(getApplicationContext(), DetailedDishInfoActivity.class);
                      //  intent.putExtra("ChooseDishResult", tvDishId.getText());
                        startActivity(intent);
                    }
                });*/

            }
            catch (Exception e)
            {
                Log.d("ERROR2", "message " + e.getMessage() );
            }

            return convertView;
        }

        private int getDishIdFromName(List<Dish> dishes, String name)
        {
            for (Dish d: dishes
                 ) {
                if(d.getName().equals(name))
                    return d.getDishId();
            }
            return  -1;
        }




    }
}

