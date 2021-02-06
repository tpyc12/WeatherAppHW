package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.weatherapp.data.City;
import com.example.weatherapp.data.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListCitiesActivity extends BaseActivity {

    private static final int SETTING_CODE = 88;

    private RecyclerView recyclerViewCities;
    private CitiesAdapter adapter;
    private ImageView imageViewSettings;
    private ImageView imageViewSearchTown;

    private MainViewModel viewModel;

    public static final List<City> cities = new ArrayList<>();

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

        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CitiesAdapter();
        adapter.setCities(cities);
        recyclerViewCities.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        LiveData<List<City>> citiesFromLiveData = viewModel.getCities();
        citiesFromLiveData.observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                adapter.setCities(cities);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewCities);

        adapter.setClickCardView(new CitiesAdapter.OnClickCardView() {
            @Override
            public void onClickCard(int position) {
                City city = adapter.getCities().get(position);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city", city.getCity());
                intent.putExtra("temp", city.getTemp());
                intent.putExtra("description", city.getDescription());
                intent.putExtra("icon", city.getIcon());
                startActivity(intent);
            }
        });
    }

    private void remove (int position) {
        City city = adapter.getCities().get(position);
        viewModel.deleteCity(city);
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