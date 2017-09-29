package com.xiongxh.bakingapp.di.component;

import com.xiongxh.bakingapp.di.module.RecipeListModule;
import com.xiongxh.bakingapp.di.scope.PerActivity;
import com.xiongxh.bakingapp.modules.recipelist.RecipeListActivity;

import dagger.Component;

@PerActivity
@Component(modules=RecipeListModule.class, dependencies = ApplicationComponent.class)
public interface RecipeListComponent {
    void inject(RecipeListActivity activity);
}
