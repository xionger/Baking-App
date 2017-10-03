package com.xiongxh.baking_app.di;

import android.content.Context;

import com.xiongxh.baking_app.BuildConfig;
import com.xiongxh.baking_app.api.RecipeApiService;
import com.xiongxh.baking_app.data.remote.RecipeServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context mContext;

    public AppModule(Context context){
        this.mContext = context;
    }

    @ApplicationContext
    @Singleton
    @Provides
    public Context provideApplicationContext(){
        return mContext;
    }

    @Singleton
    @Provides
    RecipeApiService provideRecipeApiService(){

        return RecipeServiceFactory.createFrom(RecipeApiService.class, BuildConfig.BASEURL);
    }
}
