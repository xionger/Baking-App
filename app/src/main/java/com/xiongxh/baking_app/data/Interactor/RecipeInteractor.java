package com.xiongxh.baking_app.data.Interactor;


import com.xiongxh.baking_app.data.RecipesRepository;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.local.RecipesLocalDataSource;
import com.xiongxh.baking_app.data.remote.RecipesRemoteDataSource;
import com.xiongxh.baking_app.rx.RxScheduler;

import io.reactivex.Single;

public class RecipeInteractor {
    private RecipesRepository mRecipesRepository;

    public RecipeInteractor(){
        this.mRecipesRepository =
                new RecipesRepository(new RecipesLocalDataSource(),
                        new RecipesRemoteDataSource());
    }

    public Single<Recipe> getRecipeDetail(int recipeId){
        return mRecipesRepository.getRecipe(recipeId)
                .compose(RxScheduler.applySchedulersSingle());
    }
}
