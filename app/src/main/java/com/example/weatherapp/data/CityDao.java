package com.example.weatherapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityDao {
    @Query("SELECT * FROM cities")
    LiveData<List<City>> getAllCities();

    @Query("SELECT * FROM cities WHERE id == :cityId")
    City getCityById (int cityId);

    @Query("DELETE FROM cities")
    void deleteAllCities();

    @Insert
    void insertCity (City city);

    @Delete
    void deleteCity (City city);
}
