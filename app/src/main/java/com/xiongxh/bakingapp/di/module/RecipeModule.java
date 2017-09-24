package com.xiongxh.bakingapp.di.module;

import com.xiongxh.bakingapp.api.RecipeApiService;
import com.xiongxh.bakingapp.di.scope.PerActivity;
import com.xiongxh.bakingapp.mvp.views.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RecipeModule {

    private MainView mView;

    public RecipeModule(MainView view){
        mView = view;
    }

    @PerActivity
    @Provides
    RecipeApiService provideApiService(Retrofit retrofit){
        return retrofit.create(RecipeApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return mView;
    }

}
