package com.xiongxh.bakingapp.di.component;


import android.app.Activity;

import com.xiongxh.bakingapp.di.module.RecipeModule;
import com.xiongxh.bakingapp.di.scope.PerActivity;
import com.xiongxh.bakingapp.modules.home.MainActivity;
import com.xiongxh.bakingapp.modules.home.RecipeListActivity;

import dagger.Component;

import static android.R.attr.mode;

@PerActivity
@Component(modules=RecipeModule.class, dependencies = ApplicationComponent.class)
public interface RecipeComponent {
    void inject(RecipeListActivity activity);
}
