package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.example.weatherapp.data.City;
import com.example.weatherapp.data.MainViewModel;
import com.example.weatherapp.utils.JSONUtils;
import com.example.weatherapp.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.weatherapp.Logger.DEBUG;

public class FindCityActivity extends BaseActivity {

    private EditText editTextCity;

    private String city;

    private MainViewModel viewModel;

    ArrayList<City> cities;

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
        setContentView(R.layout.activity_find_city);
        editTextCity = findViewById(R.id.editTextCity);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void downloadData() {
        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.unitsValue, NetworkUtils.lang, city);
        cities = JSONUtils.getCityFromJSON(jsonObject);
        for (City city1 : cities) {
            viewModel.insertCity(city1);
        }
        if (DEBUG) {
            Log.i("MYRES", cities.toString());
        }
    }

    public void onClickShowWeather(View view) {
        city = editTextCity.getText().toString().trim();
        if (!city.isEmpty()) {
            downloadData();
            if (!cities.isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                for (City city : cities) {
                    intent.putExtra("city", city.getCity());
                    intent.putExtra("temp", city.getTemp());
                    intent.putExtra("description", city.getDescription());
                    intent.putExtra("icon", city.getIcon());
                }
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
            downloadData();
            if (!cities.isEmpty()) {
                ListCitiesActivity.cities.addAll(cities);
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