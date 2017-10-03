package com.xiongxh.baking_app.base;

import android.app.Application;

import com.xiongxh.baking_app.di.DaggerRecipesRepositoryComponent;
import com.xiongxh.baking_app.di.AppModule;
import com.xiongxh.baking_app.di.RecipesRepositoryComponent;

public class BaseApp extends Application {

    //private AppComponent appComponent;
    private RecipesRepositoryComponent recipesRepositoryComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        recipesRepositoryComponent = initAppComponent();
    }

    protected RecipesRepositoryComponent initAppComponent(){
        return DaggerRecipesRepositoryComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public RecipesRepositoryComponent getRecipesRepositoryComponent(){
        return recipesRepositoryComponent;
    }
}
