package com.github.ugurcany.citiesbb.ui.cities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.databinding.FragmentCitiesBinding;
import com.github.ugurcany.citiesbb.model.data.City;
import com.github.ugurcany.citiesbb.ui.IMainActivity;

public class CitiesFragment extends Fragment
        implements CitiesContract.IView {

    private FragmentCitiesBinding binding;
    private CityRecyclerAdapter adapter;

    private Bundle savedState = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities,
                container, false);
        binding.setViewModel(new CitiesViewModel(this, getLifecycle(),
                savedState.getString(CitiesConstants.BUNDLE_KEY_SEARCH_INPUT, "")));

        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        binding.recyclerViewCities.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        binding.recyclerViewCities.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        binding.recyclerViewCities.setHasFixedSize(true);

        adapter = new CityRecyclerAdapter(this);
        binding.recyclerViewCities.setAdapter(adapter);
    }

    @Override
    public void updateCities(City[] cities) {
        if (adapter != null) {
            adapter.updateData(cities);
        }
    }

    @Override
    public void onClick(View view) {
        int position = binding.recyclerViewCities.getChildLayoutPosition(view);
        City city = adapter.getCityAt(position);

        ((IMainActivity) getActivity()).showMapFragment(city);
    }

    /*private void startMapApp(Coordinates coordinates) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f",
                coordinates.getLat(), coordinates.getLon());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState.putString(CitiesConstants.BUNDLE_KEY_SEARCH_INPUT,
                binding.getViewModel().searchInput.get());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            binding.getViewModel().searchInput.set(
                    savedInstanceState.getString(CitiesConstants.BUNDLE_KEY_SEARCH_INPUT, ""));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CitiesConstants.BUNDLE_KEY_SEARCH_INPUT,
                binding.getViewModel().searchInput.get());
    }

}
