package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.weatherapp.data.City;

import java.util.ArrayList;

public class ListCitiesActivity extends BaseActivity {

    private static final int SETTING_CODE = 88;

    private RecyclerView recyclerViewCities;
    private ImageView imageViewSettings;
    private ImageView imageViewSearchTown;

    private CitiesAdapter adapter;

    public static final ArrayList<City> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities);
        imageViewSearchTown = findViewById(R.id.imageButtonSearch);
        imageViewSettings = findViewById(R.id.imageButtonSettings);
        recyclerViewCities = findViewById(R.id.recyclerViewCities);
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCitiesActivity.this, SettingsActivity.class);
                startActivityForResult(intent, SETTING_CODE);
            }
        });


        imageViewSettings.setImageResource(R.drawable.settings);
        imageViewSearchTown.setImageResource(R.drawable.search);

        adapter = new CitiesAdapter(cities);
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCities.setAdapter(adapter);

        adapter.setClickCardView(new CitiesAdapter.OnClickCardView() {
            @Override
            public void onClickCard(int position) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city", cities.get(position).getCity());
                intent.putExtra("temp", cities.get(position).getTemp());
                intent.putExtra("description", cities.get(position).getDescription());
                intent.putExtra("icon", cities.get(position).getIcon());
                startActivity(intent);
            }
        });
    }

    public void onClickSearchCity(View view) {
        Intent intent = new Intent(getApplicationContext(), FindCityActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE){
            recreate();
        }
    }
}