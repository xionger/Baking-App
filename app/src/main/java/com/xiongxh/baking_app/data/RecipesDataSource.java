package com.xiongxh.baking_app.data;

import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

import java.util.List;

import io.reactivex.Single;

public interface RecipesDataSource {
    Single<List<Recipe>> getRecipes();
    Single<Recipe> getRecipe(int recipeId);

    Single<List<Ingredient>> getIngredientsOfRecipe(int recipeId);
    Single<List<Step>> getStepsOfRecipe(int recipeId);

    void setRecipes(List<Recipe> recipes);
}
