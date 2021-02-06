package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;

import com.example.weatherapp.utils.NetworkUtils;

public class SettingsActivity extends BaseActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchTheme;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchTemp;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchLang;

    public static final String MY_PREFS = "myPrefs";
    public static final String NAME_KEY = "nameKey";

    public static final String MY_PREF = "myPref";
    public static final String NAME1_KEY = "nameKey1";

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
        setContentView(R.layout.activity_settings);
        switchTheme = findViewById(R.id.switchTheme);
        switchTemp = findViewById(R.id.switchTemp);
        switchLang = findViewById(R.id.switchLanguage);
        switchTheme.setChecked(isDarkTheme());
        switchTemp.setChecked(isUnitsValue());
        switchLang.setChecked(isLangValue());
        if (isUnitsValue()) {
            NetworkUtils.unitsValue = true;
        } else {
            NetworkUtils.unitsValue = false;
        }
        if (isLangValue()) {
            NetworkUtils.lang = true;
        } else {
            NetworkUtils.lang = false;
        }

        switchTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setUnits(isChecked);
                    recreate();
                    Log.i("MYRES", String.format("%s", NetworkUtils.unitsValue));
            }
        });

        switchLang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setLang(isChecked);
                recreate();
                Log.i("MYRES", String.format("%s", NetworkUtils.lang));
            }
        });

        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDarkTheme(isChecked);
                recreate();
            }
        });
    }

    private boolean isUnitsValue() {
        SharedPreferences sharedPref = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        return sharedPref.getBoolean(NAME_KEY, false);
    }

    private void setUnits(boolean isUnitsValue){
        SharedPreferences sharedPref = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(NAME_KEY, isUnitsValue);
        editor.apply();
    }

    private boolean isLangValue() {
        SharedPreferences sharedPref = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        return sharedPref.getBoolean(NAME1_KEY, false);
    }

    private void setLang(boolean isLangValue){
        SharedPreferences sharedPref = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(NAME1_KEY, isLangValue);
        editor.apply();
    }

    public void onClickSetDarkTheme(View view) {
        switchTheme.setChecked(true);
    }

    public void onClickSetLightTheme(View view) {
        switchTheme.setChecked(false);
    }

    public void onClickSetTempC(View view) {
        switchTemp.setChecked(false);
    }

    public void onClickSetTempF(View view) {
        switchTemp.setChecked(true);
    }

    public void onClickSetRu(View view) {
        switchTemp.setChecked(false);
    }

    public void onClickSetEng(View view) {
        switchTemp.setChecked(true);
    }
}