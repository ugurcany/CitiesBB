package com.github.ugurcany.citiesbb.ui.map;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ugurcany.citiesbb.R;
import com.github.ugurcany.citiesbb.databinding.FragmentMapBinding;
import com.github.ugurcany.citiesbb.model.data.Coordinates;
import com.github.ugurcany.citiesbb.ui.MainConstants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MapFragment extends Fragment
        implements OnMapReadyCallback {

    private FragmentMapBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map,
                container, false);

        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.getMapAsync(this);

        return binding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Coordinates coordinates =
                (Coordinates) getArguments().getSerializable(MainConstants.BUNDLE_KEY_COORDINATES);
        LatLng latLng = new LatLng(coordinates.getLat(), coordinates.getLon());

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.mapView.onLowMemory();
    }

}
