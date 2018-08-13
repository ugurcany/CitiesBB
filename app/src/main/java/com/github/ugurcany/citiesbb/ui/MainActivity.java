package com.github.ugurcany.citiesbb.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.databinding.ActivityMainBinding;
import com.github.ugurcany.citiesbb.model.data.Coordinates;
import com.github.ugurcany.citiesbb.ui.cities.CitiesFragment;
import com.github.ugurcany.citiesbb.ui.map.MapFragment;

public class MainActivity extends AppCompatActivity
        implements IMainActivity {

    private ActivityMainBinding binding;

    private CitiesFragment citiesFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        if (savedInstanceState != null) {
            citiesFragment = (CitiesFragment) getSupportFragmentManager().getFragment(
                    savedInstanceState, MainConstants.FRAGMENT_KEY_CITIES);
            if (citiesFragment == null) {
                citiesFragment = new CitiesFragment();
            }

            mapFragment = (MapFragment) getSupportFragmentManager().getFragment(
                    savedInstanceState, MainConstants.FRAGMENT_KEY_MAP);
            if (mapFragment == null) {
                mapFragment = new MapFragment();
            }

            onBackStackChanged();
        } else {
            mapFragment = new MapFragment();
            citiesFragment = new CitiesFragment();

            showCitiesFragment();
        }
    }

    @Override
    public void showCitiesFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, citiesFragment, "cities");
        fragmentTransaction.commit();

        onBackStackChanged();
    }

    @Override
    public void showMapFragment(Coordinates coordinates) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainConstants.BUNDLE_KEY_COORDINATES, coordinates);
        mapFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.container_fragment, mapFragment, "map");
        fragmentTransaction.addToBackStack(getString(R.string.page_map));
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackStackChanged() {
        //HOME FRAGMENT = CITIES
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle(R.string.page_cities);
        } else {
            //GET TOP BACKSTACK ENTRY
            FragmentManager.BackStackEntry topBackStackEntry =
                    getSupportFragmentManager().getBackStackEntryAt(
                            getSupportFragmentManager().getBackStackEntryCount() - 1);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(topBackStackEntry.getName());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (citiesFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, MainConstants.FRAGMENT_KEY_CITIES,
                    citiesFragment);

        } else if (mapFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, MainConstants.FRAGMENT_KEY_MAP,
                    mapFragment);
        }
    }

}
