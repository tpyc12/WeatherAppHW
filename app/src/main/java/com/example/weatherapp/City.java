package com.example.weatherapp;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
    private String city;
    private String temp;
    private String time;

    public City(String city, String temp, String time) {
        this.city = city;
        this.temp = temp;
        this.time = time;
    }

    protected City(Parcel in) {
        city = in.readString();
        temp = in.readString();
        time = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getCity() {
        return city;
    }

    public String getTemp() {
        return temp;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(temp);
        dest.writeString(time);
    }


}
