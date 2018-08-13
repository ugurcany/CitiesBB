package com.github.ugurcany.citiesbb.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.databinding.ActivityMainBinding;
import com.github.ugurcany.citiesbb.model.data.City;
import com.github.ugurcany.citiesbb.model.data.Coordinates;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements MainContract.IView {

    private ActivityMainBinding binding;
    private CityRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MainViewModel(this, getLifecycle()));

        initRecyclerView();
    }

    private void initRecyclerView() {
        binding.recyclerViewCities.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        binding.recyclerViewCities.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        binding.recyclerViewCities.setHasFixedSize(true);

        adapter = new CityRecyclerAdapter(this);
        binding.recyclerViewCities.setAdapter(adapter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateCities(City[] cities) {
        adapter.updateData(cities);
    }

    @Override
    public void onClick(View view) {
        int position = binding.recyclerViewCities.getChildLayoutPosition(view);
        City city = adapter.getCityAt(position);

        startMapApp(city.getCoord());
    }

    private void startMapApp(Coordinates coordinates) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f",
                coordinates.getLat(), coordinates.getLon());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

}
