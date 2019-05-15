package com.example.foodtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SearchActivity extends AppCompatActivity {
    ListView lvDestinations;
    ArrayList<String> destinations;
    ArrayAdapter<String> adapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lvDestinations = findViewById(R.id.lvDestinations);
        destinations = new ArrayList<>();
        destinations.addAll(Arrays.asList(getResources().getStringArray(R.array.listOfCountries)));
        Collections.sort(destinations);

        adapter = new ArrayAdapter<String>(
                SearchActivity.this,
                android.R.layout.simple_list_item_1,
                destinations
        );

        lvDestinations.setAdapter(adapter);
        lvDestinations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String countrySearchResult  = adapter.getItem(position);
                openChooseDishActivity(countrySearchResult);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_country);

        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(destinations.contains(query)) {
                    openChooseDishActivity(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return  super.onCreateOptionsMenu(menu);
    }

    private void openChooseDishActivity(String countrySearchResult)
    {
        Intent intent = new Intent(getApplicationContext(), ChooseDishActivity.class);
        intent.putExtra("CountrySearchResult", countrySearchResult);
        startActivity(intent);
    }
}
