package com.xiongxh.baking_app.data;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;
import com.xiongxh.baking_app.data.local.RecipesLocalDataSource;
import com.xiongxh.baking_app.data.remote.RecipesRemoteDataSource;

import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RecipesRepository implements RecipesDataSource {

    private RecipesLocalDataSource mRecipesLocalDataSource;
    private RecipesRemoteDataSource mRecipesRemoteDataSource;

    public RecipesRepository(RecipesLocalDataSource localDataSource, RecipesRemoteDataSource remoteDataSource){
        this.mRecipesLocalDataSource = localDataSource;
        this.mRecipesRemoteDataSource = remoteDataSource;
    }

    public boolean isSynced(){
        return BakingApp.get().recipePreferences.isRecipesSynced();
    }

    @Override
    public Single<List<Recipe>> getRecipes() {
        return null;
    }

    @Override
    public Single<Recipe> getRecipe(int recipeId) {
        return null;
    }

    @Override
    public Single<List<Ingredient>> getIngredientsOfRecipe(int recipeId) {
        return null;
    }

    @Override
    public Single<List<Step>> getStepsOfRecipe(int recipeId) {
        return null;
    }

    @Override
    public void setRecipes(List<Recipe> recipes) {

    }
}
