package com.github.ugurcany.citiesbb.ui.activity;

import android.support.v4.app.FragmentManager;

import com.github.ugurcany.citiesbb.data.City;

public interface IMainActivity
        extends FragmentManager.OnBackStackChangedListener {

    void showCitiesFragment();

    void showMapFragment(City city);

}
