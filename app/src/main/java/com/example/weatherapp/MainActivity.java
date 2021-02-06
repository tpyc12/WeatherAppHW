package com.example.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.weatherapp.data.City;

public class MainActivity extends BaseActivity {

    private String weather = "https://yandex.ru/pogoda/%s?utm_source=serp&utm_campaign=wizard&utm_medium=desktop&utm_content=wizard_desktop_main&utm_term=title";

    private ImageView imageViewPicture;
    private ImageView imageViewWeb;
    private ImageView imageViewList;
    private TextView textViewCity;
    private TextView textViewTemp;
    private TextView textViewDescription;

    private String city = "Москва";
    private int temp = 6;
    private String description = "ясно";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemMain:
                Intent intentToMain = new Intent(this, MainActivity.class);
                startActivity(intentToMain);
                break;
            case R.id.itemListCities:
                Intent intentToListCities = new Intent(this, ListCitiesActivity.class);
                startActivity(intentToListCities);
                break;
            case R.id.itemSearch:
                Intent intentToSearch = new Intent(this, FindCityActivity.class);
                startActivity(intentToSearch);
                break;
            case R.id.itemSettings:
                Intent intentToSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentToSettings);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewPicture = findViewById(R.id.imageViewPicture);
        imageViewWeb = findViewById(R.id.imageButtonSettings);
        imageViewList = findViewById(R.id.imageButtonList);
        textViewCity = findViewById(R.id.textViewCity);
        textViewTemp = findViewById(R.id.textViewTemp);
        textViewDescription = findViewById(R.id.textViewDescription);

        Intent intent = getIntent();
        if (intent.hasExtra("city")) {
            city = intent.getStringExtra("city");
            temp = intent.getIntExtra("temp", -1);
            description = intent.getStringExtra("description");
            Glide.with(this).load(intent.getStringExtra("icon")).into(imageViewPicture);
        }

        imageViewWeb.setImageResource(R.drawable.www);
        imageViewList.setImageResource(R.drawable.list);
        textViewCity.setText(city);
        textViewTemp.setText(temp + "°");
        textViewDescription.setText(description);
    }

    public void onClickShowWeb(View view) {
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(weather, city)));
        startActivity(browseIntent);
    }

    public void onClickShowListTowns(View view) {
        Intent intent = new Intent(getApplicationContext(), ListCitiesActivity.class);
        startActivity(intent);
    }
}