package com.github.ugurcany.citiesbb.model;

import android.content.Context;

import com.github.ugurcany.citiesbb.model.city.CityModel;

public class ModelProvider {

    private CityModel cityModel;

    public ModelProvider(Context context) {
        cityModel = new CityModel(context);
    }

    public CityModel cityModel() {
        return cityModel;
    }

}
