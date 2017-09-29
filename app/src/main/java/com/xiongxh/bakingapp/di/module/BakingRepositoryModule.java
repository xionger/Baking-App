package com.xiongxh.bakingapp.di.module;

import com.xiongxh.bakingapp.data.DataService;
import com.xiongxh.bakingapp.data.local.Local;
import com.xiongxh.bakingapp.data.remote.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BakingRepositoryModule {
    @Singleton
    @Provides
    @Local
    DataService provideBakingLocalDataService(){
        return null;
    }

    @Singleton
    @Provides
    @Remote
    DataService provideBakingRemoteDataService(){
        return null;
    }
}
