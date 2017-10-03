package com.xiongxh.baking_app.di;

import com.xiongxh.baking_app.data.RecipesRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RecipesRepositoryModule.class, AppModule.class})
public interface RecipesRepositoryComponent {

    public RecipesRepository getRecipesRepository();
}
