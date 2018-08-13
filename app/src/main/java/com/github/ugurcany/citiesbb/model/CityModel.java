package com.github.ugurcany.citiesbb.model;

import android.content.Context;
import android.content.res.AssetManager;

import com.github.ugurcany.citiesbb.model.data.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.TreeMap;

public class CityModel implements ICityModel {

    private final static String FILE_CITIES = "data/cities.json";

    private Context context;

    /*
     * TREE-BASED NAVIGABLE MAP
     * -> HOLDS ENTRIES IN SORTED KEY ORDER
     * -> PROVIDES SUBMAP(...) FUNCTION TO GET A PORTION OF MAP
     * -> CREATING SUBMAP TAKES O(1) & ACCESS TAKES O(LOGN)
     */
    private TreeMap<String, City> cityMap;

    public CityModel(Context context) {
        this.context = context;
        initCityMap();
    }

    private void initCityMap() {
        cityMap = new TreeMap<>();

        InputStream ims = null;
        try {
            AssetManager assetManager = context.getAssets();
            ims = assetManager.open(FILE_CITIES);

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(ims);

            List<City> cityList = gson.fromJson(reader, new TypeToken<List<City>>() {
            }.getType());
            for (City city : cityList) {
                cityMap.put(city.getDisplayName(), city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ims != null) {
                try {
                    ims.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public City[] getAllCities() {
        return cityMap.values().toArray(new City[0]);
    }

    @Override
    public City[] getCitiesStartingWith(String prefix) {
        return cityMap.subMap(prefix, prefix + Character.MAX_VALUE)
                .values().toArray(new City[0]);
    }

}
