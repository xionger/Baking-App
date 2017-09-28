package com.xiongxh.bakingapp.application;

import android.app.Application;

import com.xiongxh.bakingapp.BuildConfig;
import com.xiongxh.bakingapp.di.component.ApplicationComponent;
import com.xiongxh.bakingapp.di.component.DaggerApplicationComponent;
import com.xiongxh.bakingapp.di.module.ApplicationModule;

public class RecipeApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, BuildConfig.BASEURL))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
