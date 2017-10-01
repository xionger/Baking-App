package com.xiongxh.bakingapp.di.component;

import android.content.Context;

import com.xiongxh.bakingapp.di.module.AppModule;
import com.xiongxh.bakingapp.di.scope.ApplicationContext;
import com.xiongxh.bakingapp.modules.recipelist.RecipeListActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(RecipeListActivity activity);

}
