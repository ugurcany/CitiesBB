package com.github.ugurcany.citiesbb.ui.cities;

import android.arch.lifecycle.LifecycleObserver;
import android.view.View;

import com.github.ugurcany.citiesbb.data.City;

interface CitiesContract {

    interface IView extends View.OnClickListener {

        void updateCities(City[] cities);

    }

    interface IViewModel extends LifecycleObserver {

    }

}
