package com.github.ugurcany.citiesbb.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.databinding.ActivityMainBinding;
import com.github.ugurcany.citiesbb.model.data.Coordinates;
import com.github.ugurcany.citiesbb.ui.cities.CitiesFragment;
import com.github.ugurcany.citiesbb.ui.map.MapFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements IMainActivity {

    private ActivityMainBinding binding;

    private HashMap<String, Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        fragments = new HashMap<>();
        fragments.put(MainConstants.FRAGMENT_KEY_CITIES, new CitiesFragment());
        fragments.put(MainConstants.FRAGMENT_KEY_MAP, new MapFragment());

        if (savedInstanceState != null) {
            for (String fragmentKey : fragments.keySet()) {
                Fragment fragment = getSupportFragmentManager().getFragment(
                        savedInstanceState, fragmentKey);

                if (fragment != null) {
                    fragments.put(fragmentKey, fragment);
                }
            }
        } else {
            showCitiesFragment();
        }

        setToolbar();
    }

    @Override
    public void showCitiesFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();

        Fragment fragment = fragments.get(MainConstants.FRAGMENT_KEY_CITIES);

        fragmentTransaction.replace(R.id.container_fragment,
                fragment, MainConstants.FRAGMENT_KEY_CITIES);
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showMapFragment(Coordinates coordinates) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();

        Fragment fragment = fragments.get(MainConstants.FRAGMENT_KEY_MAP);

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainConstants.BUNDLE_KEY_COORDINATES, coordinates);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.container_fragment,
                fragment, MainConstants.FRAGMENT_KEY_MAP);
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
        setToolbar();
    }

    private void setToolbar() {
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

        for (String fragmentKey : fragments.keySet()) {
            Fragment fragment = fragments.get(fragmentKey);

            if (fragment.isAdded()) {
                getSupportFragmentManager().putFragment(outState, fragmentKey, fragment);
            }
        }
    }

}
