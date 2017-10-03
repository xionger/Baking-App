package com.xiongxh.baking_app.di;

import com.xiongxh.baking_app.recipes.RecipesActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(RecipesActivity activity);
}
