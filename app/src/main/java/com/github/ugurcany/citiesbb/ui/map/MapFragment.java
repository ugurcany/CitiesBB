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

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map,
                container, false);

        return binding.getRoot();
    }

}
