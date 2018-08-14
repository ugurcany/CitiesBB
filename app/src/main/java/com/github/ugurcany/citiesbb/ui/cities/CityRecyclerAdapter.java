package com.github.ugurcany.citiesbb.ui.cities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.model.data.City;

public class CityRecyclerAdapter
        extends RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder> {

    private City[] data;
    private View.OnClickListener onClickListener;

    CityRecyclerAdapter(View.OnClickListener onClickListener) {
        this.data = new City[0];
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_city_item, parent, false);
        textView.setOnClickListener(onClickListener);
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

    public void updateData(City[] newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    public City getCityAt(int position) {
        return data[position];
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