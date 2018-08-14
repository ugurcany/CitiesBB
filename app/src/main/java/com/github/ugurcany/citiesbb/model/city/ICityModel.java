package com.github.ugurcany.citiesbb.model.city;

import com.github.ugurcany.citiesbb.data.City;

public interface ICityModel {

    City[] getAllCities();

    City[] getCitiesStartingWith(String prefix);

}
