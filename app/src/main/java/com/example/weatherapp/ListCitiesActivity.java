package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListCitiesActivity extends AppCompatActivity {

    private String weather = "https://yandex.ru/pogoda";

    private RecyclerView recyclerViewCities;
    private ImageView imageViewWeb;
    private ImageView imageViewSearchTown;

    private ArrayList<City> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities);
        imageViewSearchTown = findViewById(R.id.imageButtonSearch);
        imageViewWeb = findViewById(R.id.imageButtonWeb);
        recyclerViewCities = findViewById(R.id.recyclerViewCities);

        imageViewWeb.setImageResource(R.drawable.www);
        imageViewSearchTown.setImageResource(R.drawable.search);

        Date currentDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        cities.add(new City("Moscow", "-1", timeText));
        cities.add(new City("Minsk", "+4", timeText));
        cities.add(new City("London", "+6", timeText));
        cities.add(new City("Kiev", "+3", timeText));
        cities.add(new City("Kalinigrad", "+10", timeText));

        CitiesAdapter adapter = new CitiesAdapter(cities);
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCities.setAdapter(adapter);
        adapter.setClickCardView(new CitiesAdapter.OnClickCardView() {
            @Override
            public void onClickCard(int position) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city", cities.get(position).getCity());
                intent.putExtra("temp", cities.get(position).getTemp());
                startActivity(intent);
            }
        });
    }

    public void onClickSearchCity(View view) {
        Intent intent = new Intent(getApplicationContext(), FindCityActivity.class);
        startActivity(intent);
    }

    public void onClickShowWeb(View view) {
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(weather));
        startActivity(browseIntent);
    }
}