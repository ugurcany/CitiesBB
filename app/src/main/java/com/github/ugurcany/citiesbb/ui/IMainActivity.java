package com.github.ugurcany.citiesbb.ui;

import android.support.v4.app.FragmentManager;

import com.github.ugurcany.citiesbb.model.data.City;

public interface IMainActivity
        extends FragmentManager.OnBackStackChangedListener {

    void showCitiesFragment();

    void showMapFragment(City city);

}
