package com.xiongxh.baking_app.di;

import com.xiongxh.baking_app.recipes.RecipesActivity;

import dagger.Component;

@RecipesActivityScope
@Component(dependencies = RecipesRepositoryComponent.class, modules = RecipesModule.class)
public interface RecipesComponent {
    void inject(RecipesActivity activity);
}
