package com.example.weatherapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class CityDatabase extends RoomDatabase {

    private static final String DB_NAME = "cities.db";
    private static CityDatabase database;
    private static final Object LOCK = new Object();

    public static CityDatabase getInstance(Context context){
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, CityDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }

    public abstract CityDao cityDao();

}
