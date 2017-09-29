package com.xiongxh.bakingapp.di.component;

import com.xiongxh.bakingapp.application.BakingApplicationModule;
import com.xiongxh.bakingapp.data.BakingRepository;
import com.xiongxh.bakingapp.di.module.BakingRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {BakingRepositoryModule.class, BakingApplicationModule.class})
public interface BakingRepositoryComponent {
    BakingRepository getBakingRepository();

}
