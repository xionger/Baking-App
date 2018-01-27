package com.xiongxh.baking_app.base;

import com.xiongxh.baking_app.recipedetail.RecipeDetailContract;
import com.xiongxh.baking_app.recipedetail.RecipeDetailPresenter;
import com.xiongxh.baking_app.recipes.RecipesContract;
import com.xiongxh.baking_app.recipes.RecipesPresenter;
import com.xiongxh.baking_app.recipesteps.RecipeStepsContract;
import com.xiongxh.baking_app.recipesteps.RecipeStepsPresenter;

public class PresenterProvider {
    public RecipesContract.Presenter provideRecipes() {
        return new RecipesPresenter();
    }

    public RecipeDetailContract.Presenter provideRecipeDetails() {
        return new RecipeDetailPresenter();
    }

    public RecipeStepsContract.Presenter provideSteps() {
        return new RecipeStepsPresenter();
    }
}
