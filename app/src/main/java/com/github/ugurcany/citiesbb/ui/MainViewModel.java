package com.github.ugurcany.citiesbb.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.github.ugurcany.citiesbb.model.CityModel;
import com.github.ugurcany.citiesbb.model.ICityModel;
import com.github.ugurcany.citiesbb.model.data.City;

public class MainViewModel implements MainContract.IViewModel {

    public ObservableField<String> searchInput = new ObservableField<>("");
    public ObservableField<Boolean> noResultMsgVisible = new ObservableField<>(false);

    private MainContract.IView view;
    private ICityModel cityModel;

    MainViewModel(MainContract.IView view, Lifecycle lifecycle) {
        this.view = view;
        this.cityModel = new CityModel(view.getContext());

        this.searchInput.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                onSearchInputChanged(searchInput.get());
            }
        });

        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onViewCreated() {
        //INITIALLY NO FILTER APPLIED
        onSearchInputChanged("");
    }

    private void onSearchInputChanged(String input) {
        City[] cities;
        if (TextUtils.isEmpty(input)) {
            cities = cityModel.getAllCities();
        } else {
            cities = cityModel.getCitiesStartingWith(input);
        }

        //SHOW NO RESULT MSG?
        noResultMsgVisible.set(cities == null || cities.length == 0);

        //UPDATE VIEW
        view.updateCities(cities);
    }

}
