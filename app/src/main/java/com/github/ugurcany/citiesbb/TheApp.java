package com.github.ugurcany.citiesbb;

import android.app.Application;

import com.github.ugurcany.citiesbb.model.ModelProvider;

public class TheApp extends Application {

    private static ModelProvider modelProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        modelProvider = new ModelProvider(getApplicationContext());
    }

    public static ModelProvider modelProvider() {
        return modelProvider;
    }

}
