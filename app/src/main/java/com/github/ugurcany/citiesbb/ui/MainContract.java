package com.github.ugurcany.citiesbb.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.view.View;

import com.github.ugurcany.citiesbb.model.data.City;

interface MainContract {

    interface IView extends View.OnClickListener {

        Context getContext();

        void updateCities(City[] cities);

    }

    interface IViewModel extends LifecycleObserver {


    }

}
