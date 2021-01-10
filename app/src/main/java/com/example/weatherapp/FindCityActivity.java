package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.data.City;
import com.example.weatherapp.data.MainViewModel;
import com.example.weatherapp.utils.JSONUtils;
import com.example.weatherapp.utils.NetworkUtils;
import org.json.JSONObject;

public class FindCityActivity extends BaseActivity {

    private EditText editTextCity;

    private String city;
    private City cityName;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_city);
        editTextCity = findViewById(R.id.editTextCity);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void showWeather() {
        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.unitsValue, NetworkUtils.lang, city);
        cityName = JSONUtils.getCityFromJSON(jsonObject);
    }

    public void onClickShowWeather(View view) {
        city = editTextCity.getText().toString().trim();
        if (!city.isEmpty()) {
            showWeather();
            if (cityName.getCity() != null) {
                ListCitiesActivity.cities.add(cityName);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city", cityName.getCity());
                intent.putExtra("temp", cityName.getTemp());
                intent.putExtra("description", cityName.getDescription());
                intent.putExtra("icon", cityName.getIcon());
                startActivity(intent);
            } else {
                Toast.makeText(this, "По Вашему запросу не найдено городов, попробуйте еще", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Введите город", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickAddWeather(View view) {
        city = editTextCity.getText().toString().trim();
        if (!city.isEmpty()) {
            showWeather();
            if (cityName.getCity() != null) {
                ListCitiesActivity.cities.add(cityName);
                Intent intent = new Intent(getApplicationContext(), ListCitiesActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "По Вашему запросу не найдено городов, попробуйте еще", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Введите город", Toast.LENGTH_SHORT).show();
        }
    }

}