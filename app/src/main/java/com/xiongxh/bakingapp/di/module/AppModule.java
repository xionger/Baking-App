package com.xiongxh.bakingapp.di.module;

import android.content.Context;

import com.xiongxh.bakingapp.BuildConfig;
import com.xiongxh.bakingapp.api.RecipeApiService;
import com.xiongxh.bakingapp.data.remote.RecipeServiceFactory;
import com.xiongxh.bakingapp.di.scope.ApplicationContext;

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
    Context provideApplicationContext(){
        return mContext;
    }

    @Singleton
    @Provides
    RecipeApiService provideRecipeApiService(){

        return RecipeServiceFactory.createFrom(RecipeApiService.class, BuildConfig.BASEURL);
    }
}
