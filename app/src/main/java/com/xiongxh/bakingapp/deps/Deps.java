package com.xiongxh.bakingapp.deps;

import com.xiongxh.bakingapp.home.HomeActivity;
import com.xiongxh.bakingapp.networks.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}