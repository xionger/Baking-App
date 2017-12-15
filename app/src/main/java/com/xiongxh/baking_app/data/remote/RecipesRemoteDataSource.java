package com.xiongxh.baking_app.data.remote;

import com.xiongxh.baking_app.api.RecipeApiService;
import com.xiongxh.baking_app.data.RecipesDataSource;
import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

import java.util.List;

import io.reactivex.Single;

public class RecipesRemoteDataSource implements RecipesDataSource{
    @Override
    public Single<List<Recipe>> getRecipes() {
        return RecipeApiService.initService().fetchRecipesFromServer();
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
