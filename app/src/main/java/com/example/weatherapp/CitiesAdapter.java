package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.data.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter <CitiesAdapter.CitiesViewHolder>{

    private List<City> cities;
    private OnClickCardView clickCardView;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public void addCities (List<City> cities){
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }

    public CitiesAdapter() {
        cities = new ArrayList<>();
    }

    interface OnClickCardView{
        void onClickCard(int position);
    }

    public void setClickCardView(OnClickCardView clickCardView) {
        this.clickCardView = clickCardView;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_info_item, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        City city = cities.get(position);
        holder.textViewCity.setText(city.getCity());
        holder.textViewTime.setText(city.getTime());
        holder.textViewTemp.setText(String.format("%s", city.getTemp()));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class CitiesViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewCity;
        private TextView textViewTemp;
        private TextView textViewTime;

        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.textViewCity);
            textViewTemp = itemView.findViewById(R.id.textViewTemp);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickCardView != null){
                        clickCardView.onClickCard(getAdapterPosition());
                    }
                }
            });
        }
    }
}
