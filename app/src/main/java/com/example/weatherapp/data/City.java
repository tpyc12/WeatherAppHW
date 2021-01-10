package com.example.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities")
public class City implements Parcelable {
    @PrimaryKey
    private int id;
    private String city;
    private int temp;
    private String time;
    private String description;
    private String icon;

    public City(int id, String city, int temp, String time, String description, String icon) {
        this.id = id;
        this.city = city;
        this.temp = temp;
        this.time = time;
        this.description = description;
        this.icon = icon;
    }

    @Ignore
    public City() {
    }

    protected City(Parcel in) {
        id = in.readInt();
        city = in.readString();
        temp = in.readInt();
        time = in.readString();
        description = in.readString();
        icon = in.readString();
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

    public int getTemp() {
        return temp;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(city);
        dest.writeInt(temp);
        dest.writeString(time);
        dest.writeString(description);
        dest.writeString(icon);
    }
}
