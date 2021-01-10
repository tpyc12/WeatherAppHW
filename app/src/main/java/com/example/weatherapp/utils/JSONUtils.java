package com.example.weatherapp.utils;

import com.example.weatherapp.data.City;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class JSONUtils {

    private static final String BASE_ICON_URL = "https://openweathermap.org/img/wn/%s@4x.png";

    private static final String KEY_ICON = "icon";
    private static final String KEY_TIMEZONE = "timezone";
    private static final String KEY_CITY = "name";
    private static final String KEY_MAIN_TEMP = "main";
    private static final String KEY_TEMP = "temp";
    private static final String KEY_WEATHER_DESC = "weather";
    private static final int KEY_OBJECT_DESC = 0;
    private static final String KEY_DESC = "description";
    private static final String KEY_ID = "id";



    public static City getCityFromJSON (JSONObject jsonObject){
        City result = new City();
        if (jsonObject == null){
            return result;
        }
        try {
           long timezone = jsonObject.getLong(KEY_TIMEZONE) * 1000;
           DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
           timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+00"));
           String date = timeFormat.format(new Date().getTime() + timezone);
           String city = jsonObject.getString(KEY_CITY);
           int temp = jsonObject.getJSONObject(KEY_MAIN_TEMP).getInt(KEY_TEMP);
           String description = jsonObject.getJSONArray(KEY_WEATHER_DESC).getJSONObject(KEY_OBJECT_DESC).getString(KEY_DESC);
           int id = jsonObject.getInt(KEY_ID);
           String icon = String.format(BASE_ICON_URL, jsonObject.getJSONArray(KEY_WEATHER_DESC).getJSONObject(KEY_OBJECT_DESC).getString(KEY_ICON));
           result = new City(id, city, temp, date, description, icon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
