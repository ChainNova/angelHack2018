package com.angehack.sharedparking.module.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import com.angehack.sharedparking.api.DaggerDeps;
import com.angehack.sharedparking.api.Deps;
import com.angehack.sharedparking.api.NetworkModule;
import com.angehack.sharedparking.api.NetworkServiceInter;

import java.io.File;

import javax.inject.Inject;


public class BaseFragment extends Fragment {

    @Inject
    public NetworkServiceInter service;
    private Deps deps;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getActivity().getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
        deps.inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
