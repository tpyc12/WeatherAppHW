package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends BaseActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchTheme;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchTemp;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchLang;

//    public EditText editTextCity;
//
//    private String city = editTextCity.getText().toString().trim();
//    private City cityName;
//
//    private static boolean lang;
//    private static boolean metric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        editTextCity = findViewById(R.id.editTextCity);
        switchTheme = findViewById(R.id.switchTheme);
        switchTemp = findViewById(R.id.switchTemp);
        switchLang = findViewById(R.id.switchLanguage);
        switchTheme.setChecked(isDarkTheme());

//        switchTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                setLangMethod(isChecked);
//            }
//        });




//        switchLang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                boolean lang;
//                if (isChecked){
//                    lang = NetworkUtils.RU;
//                } else {
//                    lang = NetworkUtils.EN;
//                }
//                JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.unitsValue, lang, city);
//                cityName = JSONUtils.getCityFromJSON(jsonObject);
//            }
//        });
        
        
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDarkTheme(isChecked);
                recreate();
            }
        });
    }
//
//    public void setLangMethod(boolean isRU) {
//        if (isRU) {
//            lang = NetworkUtils.RU;
//        } else {
//            lang = NetworkUtils.EN;
//        }
//        downloadData(NetworkUtils.METRIC, lang, city);
//    }

//    private void downloadData(boolean unitsValue, boolean lang, String city){
//        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.unitsValue, NetworkUtils.lang, city);
//        cityName = JSONUtils.getCityFromJSON(jsonObject);
//    }

    public void onClickSetDarkTheme(View view) {
        switchTheme.setChecked(false);
    }

    public void onClickSetLightTheme(View view) {
        switchTheme.setChecked(true);
    }

    public void onClickSetTempC(View view) {
        switchTemp.setChecked(false);
    }

    public void onClickSetTempF(View view) {
        switchTemp.setChecked(true);
    }

}