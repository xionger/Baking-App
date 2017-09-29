package com.xiongxh.bakingapp.application;


import android.app.Application;

import com.xiongxh.bakingapp.di.component.BakingRepositoryComponent;

public class BakingApplication extends Application {

    private BakingRepositoryComponent mBakingRepositoryComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        mBakingRepositoryComponent = DaggerBakingRepositoryComponent.builder()
                .bakingApplicationModule(new BakingApplicationModule(getApplicationContext()))
                .build();
    }

    public BakingRepositoryComponent getBakingRepositoryComponent(){
        return mBakingRepositoryComponent;
    }
}
