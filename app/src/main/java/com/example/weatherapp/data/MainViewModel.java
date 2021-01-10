package com.example.weatherapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static CityDatabase database;
    LiveData<List<City>> cities;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = CityDatabase.getInstance(getApplication());
        cities = database.cityDao().getAllCities();
    }

    public City getCityById(int id){
        try {
            return new GetCityTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllCities(){
        new DeleteCitiesTask().execute();
    }

    public void insertCity(City city){
        new InsertTask().execute(city);
    }

    public void deleteCity(City city){
        new InsertTask().execute(city);
    }

    private static class DeleteTask extends AsyncTask<City, Void, Void>{

        @Override
        protected Void doInBackground(City... cities) {
            if (cities != null || cities.length > 0){
                database.cityDao().deleteCity(cities[0]);
            }
            return null;
        }
    }

    private static class InsertTask extends AsyncTask<City, Void, Void>{

        @Override
        protected Void doInBackground(City... cities) {
            if (cities != null || cities.length > 0){
                database.cityDao().insertCity(cities[0]);
            }
            return null;
        }
    }

    private static class DeleteCitiesTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... integers) {
            database.cityDao().deleteAllCities();
            return null;
        }
    }

    private static class GetCityTask extends AsyncTask<Integer, Void, City>{

        @Override
        protected City doInBackground(Integer... integers) {
            if (integers != null || integers.length > 0){
                return database.cityDao().getCityById(integers[0]);
            }
            return null;
        }
    }
}
