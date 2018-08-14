package com.github.ugurcany.citiesbb.ui.cities;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.github.ugurcany.citiesbb.TheApp;
import com.github.ugurcany.citiesbb.model.ICityModel;
import com.github.ugurcany.citiesbb.model.data.City;

public class CitiesViewModel implements CitiesContract.IViewModel {

    public ObservableField<String> searchInput = new ObservableField<>("");
    public ObservableField<Boolean> noResultMsgVisible = new ObservableField<>(false);

    private CitiesContract.IView view;
    private ICityModel cityModel;

    CitiesViewModel(CitiesContract.IView view, Lifecycle lifecycle, String initialSearchInput) {
        this.view = view;
        this.cityModel = TheApp.modelProvider().cityModel();

        this.searchInput.set(initialSearchInput);
        this.searchInput.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                onSearchInputChanged(searchInput.get());
            }
        });

        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onViewStarted() {
        onSearchInputChanged(searchInput.get());
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
