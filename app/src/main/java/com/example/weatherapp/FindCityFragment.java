package com.example.weatherapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;


public class FindCityFragment extends Fragment {

    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=6ae260b1fd6b6df0a257fff6e602c5bb&lang=ru&units=metric";

    private EditText editTextCity;
    private Button buttonShowCity;
    private Button buttonAddCity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextCity = view.findViewById(R.id.editTextCity);
        buttonAddCity = view.findViewById(R.id.buttonAddCity);
        buttonShowCity = view.findViewById(R.id.buttonShowCity);


    }

    private void showWeather(){
        try {
            final URL url = new URL(String.format(WEATHER_URL, editTextCity.getText().toString().trim()));
            final Handler handler = new Handler();

            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    HttpURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);
                        JSONObject jsonObject = new JSONObject(result);
                        String name = jsonObject.getString("name");
                        String temp = jsonObject.getJSONObject("main").getString("temp");
                        String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }finally {
                        if (urlConnection != null){
                            urlConnection.disconnect();
                        }
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines (BufferedReader in){
        return in.lines().collect(Collectors.joining("\n"));
    }

    private void onClickShowWeather (View view){
        String city = editTextCity.getText().toString().trim();
        if (!city.isEmpty()){
            showWeather();

        }

    }

    private void onClickAddWeather (View view){

    }

}




