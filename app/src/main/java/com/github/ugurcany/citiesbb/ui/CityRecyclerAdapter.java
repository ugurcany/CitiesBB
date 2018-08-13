package com.github.ugurcany.citiesbb.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.model.data.City;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder> {

    private City[] data;

    CityRecyclerAdapter(City[] data) {
        this.data = data;
    }

    @Override
    @NonNull
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_city_item, parent, false);
        return new CityViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.textView.setText(data[position].getDisplayName());
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void updateData(City[] newData){
        this.data = newData;
        notifyDataSetChanged();
    }

    //VIEWHOLDER
    static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        CityViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}