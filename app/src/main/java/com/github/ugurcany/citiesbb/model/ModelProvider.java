package com.github.ugurcany.citiesbb.model;

import android.content.Context;

public class ModelProvider {

    private CityModel cityModel;

    public ModelProvider(Context context) {
        cityModel = new CityModel(context);
    }

    public CityModel cityModel() {
        return cityModel;
    }

}
