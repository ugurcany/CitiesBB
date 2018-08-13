package com.github.ugurcany.citiesbb.model;

import com.github.ugurcany.citiesbb.model.data.City;

public interface ICityModel {

    City[] getAllCities();

    City[] getCitiesStartingWith(String prefix);

}
