package com.example.weatherapp.utils;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    private static final String PARAMS_API_KEY = "appid";
    private static final String PARAMS_CITY = "q";
    private static final String PARAMS_UNITS = "units";
    private static final String PARAMS_LANG = "lang";

    private static final String API_KEY = "6ae260b1fd6b6df0a257fff6e602c5bb";
    private static final String LANGUAGE_VALUE_RU = "ru";
    private static final String LANGUAGE_VALUE_EN = "en";
    private static final String UNITS_VALUE_METRIC = "metric";
    private static final String UNITS_VALUE_IMPERIAL = "imperial";

    public static final boolean METRIC = true;
    public static final boolean IMPERIAL = false;

    public static final boolean RU = true;
    public static final boolean EN = false;

    public static boolean unitsValue = true;
    public static boolean lang = true;

    public static URL buildURL(boolean unitsValue, boolean lang, String city){
        URL result = null;
        String langValue;
        String methodOfUnits;
        if (!unitsValue){
            methodOfUnits = UNITS_VALUE_IMPERIAL;
        }else {
            methodOfUnits = UNITS_VALUE_METRIC;
        }
        if (lang) {
            langValue = LANGUAGE_VALUE_RU;
        } else {
            langValue = LANGUAGE_VALUE_EN;
        }
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAMS_CITY, city)
                .appendQueryParameter(PARAMS_API_KEY, API_KEY)
                .appendQueryParameter(PARAMS_LANG, langValue)
                .appendQueryParameter(PARAMS_UNITS, methodOfUnits)
                .build();
        try {
            result = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject getJSONFromNetwork (boolean unitsValue, boolean lang, String city){
        JSONObject result = null;
        URL url = buildURL(unitsValue, lang, city);
        try {
            result = new JSONLoadTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static class JSONLoadTask extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject result = null;
            if (urls == null || urls.length == 0){
                return result;
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) urls[0].openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                while (line != null){
                    builder.append(line);
                    line = reader.readLine();
                }
                result = new JSONObject(builder.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
            return result;
        }
    }
}
