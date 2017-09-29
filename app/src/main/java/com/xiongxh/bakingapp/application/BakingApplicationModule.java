package com.xiongxh.bakingapp.application;

import android.content.Context;

import com.xiongxh.bakingapp.api.RecipeApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BakingApplicationModule {

    private final Context mContext;

    public BakingApplicationModule(Context context){
        this.mContext = context;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    RecipeApiService provideRecipeApiService(){
        return null;
    }
}
