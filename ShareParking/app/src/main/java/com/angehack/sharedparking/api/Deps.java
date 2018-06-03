package com.angehack.sharedparking.api;


import com.angehack.sharedparking.module.common.BaseActivity;
import com.angehack.sharedparking.module.common.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ennur on 6/28/16.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface Deps {

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

}
