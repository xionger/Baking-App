package com.xiongxh.bakingapp.di.module;

import com.xiongxh.bakingapp.mvp.views.RecipeListViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeListModule {
    private final RecipeListViewContract.View mView;

    RecipeListModule(RecipeListViewContract.View view){
        this.mView = view;
    }

    @Provides
    RecipeListViewContract.View provideRecipeListView() {
        return mView;
    }
}
