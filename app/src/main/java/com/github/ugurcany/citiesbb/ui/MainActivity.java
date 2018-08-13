package com.github.ugurcany.citiesbb.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.databinding.ActivityMainBinding;
import com.github.ugurcany.citiesbb.model.data.City;

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

        adapter = new CityRecyclerAdapter(new City[0]);
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

}
