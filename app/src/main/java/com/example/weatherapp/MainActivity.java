package com.example.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends BaseActivity {

    private String weather = "https://yandex.ru/pogoda/%s?utm_source=serp&utm_campaign=wizard&utm_medium=desktop&utm_content=wizard_desktop_main&utm_term=title";

    private ImageView imageViewPicture;
    private ImageView imageViewWeb;
    private ImageView imageViewList;
    private TextView textViewCity;
    private TextView textViewTemp;
    private TextView textViewDescription;

    private String city;
    private int temp;
    private String description;

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
