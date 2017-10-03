package com.xiongxh.baking_app.di;

import com.xiongxh.baking_app.recipes.RecipesContract;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipesModule {

    private final RecipesContract.View mView;

    public RecipesModule(RecipesContract.View view){
        this.mView = view;
    }

    @Provides
    public RecipesContract.View provideRecipesContractView(){
        return mView;
    }
}
