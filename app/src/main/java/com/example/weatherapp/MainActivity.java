package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=6ae260b1fd6b6df0a257fff6e602c5bb&lang=ru&units=metric";

    private String weather = "https://yandex.ru/pogoda/%s?utm_source=serp&utm_campaign=wizard&utm_medium=desktop&utm_content=wizard_desktop_main&utm_term=title";

    private ImageView imageViewPicture;
    private ImageView imageViewWeb;
    private ImageView imageViewList;
    private TextView textViewCity;
    private TextView textViewTemp;

    private String city = "";
    private String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewPicture = findViewById(R.id.imageViewPicture);
        imageViewWeb = findViewById(R.id.imageButtonWeb);
        imageViewList = findViewById(R.id.imageButtonList);
        textViewCity = findViewById(R.id.textViewCity);
        textViewTemp = findViewById(R.id.textViewTemp);

        Intent intent = getIntent();

        city = intent.getStringExtra("city");
        temp = intent.getStringExtra("temp");

        imageViewPicture.setImageResource(R.drawable.sun);
        imageViewWeb.setImageResource(R.drawable.www);
        imageViewList.setImageResource(R.drawable.list);
        textViewCity.setText(city);
        textViewTemp.setText(temp);

    }

    public void onClickShowWeb(View view) {
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(weather, city)));
        startActivity(browseIntent);
    }

    public void onClickShowListTowns(View view) {
        Intent intent = new Intent(getApplicationContext(), ListCitiesActivity.class);
        startActivity(intent);
    }

//    public void onClickShowWeather(View view) {
//        String city = editTextCity.getText().toString().trim();
//        if (!city.isEmpty()){
//            DownloadWeatherTask task = new DownloadWeatherTask();
//            String url = String.format(WEATHER_URL, city);
//            task.execute(url);
//        }
//    }

//    private class DownloadWeatherTask extends AsyncTask<String, Void, String>{
//        @Override
//        protected String doInBackground(String... strings) {
//            URL url = null;
//            HttpURLConnection urlConnection = null;
//            StringBuilder result = new StringBuilder();
//            try {
//                url = new URL(strings[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = urlConnection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader reader = new BufferedReader(inputStreamReader);
//                String line = reader.readLine();
//                while (line != null){
//                    result.append(line);
//                    line = reader.readLine();
//                }
//                return result.toString();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null){
//                    urlConnection.disconnect();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                String name = jsonObject.getString("name");
//                String temp = jsonObject.getJSONObject("main").getString("temp");
//                String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
//                String weather = String.format("%s\nТемпература: %s\nНа улице: %s", name, temp,description);
//                textViewWeatherInfo.setText(weather);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}