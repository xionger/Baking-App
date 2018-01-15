package com.xiongxh.baking_app.data.local;


import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.data.RecipesDataSource;
import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class RecipesLocalDataSource implements RecipesDataSource {

    RecipesDao mRecipesDao;

    public RecipesLocalDataSource(){
        mRecipesDao = BakingApp.get().database.recipesDao();
    }

    public RecipesLocalDataSource(RecipesDao dao){
        this.mRecipesDao = dao;
    }

    private Observable<Recipe> getIngredientsAndSteps(Recipe recipe){
        return Observable.just(recipe).map(recipe1 -> {
            recipe1.setIngredients(mRecipesDao.getIngredients(recipe1.getId()));
            recipe1.setSteps(mRecipesDao.getSteps(recipe1.getId()));
            return recipe1;
        });
    }

    @Override
    public Single<List<Recipe>> getRecipes() {
        return Observable.fromCallable(() -> mRecipesDao.getRecipes())
            .flatMap(Observable::fromIterable)
            .flatMap(this::getIngredientsAndSteps)
            .toList();
    }

    @Override
    public Single<Recipe> getRecipe(int recipeId) {
        return Observable.fromCallable(()-> mRecipesDao.getRecipe(recipeId))
                .flatMap(this::getIngredientsAndSteps)
                .firstOrError();
    }

    @Override
    public Single<List<Ingredient>> getIngredientsOfRecipe(int recipeId) {
        return Single.fromCallable(() -> mRecipesDao.getIngredients(recipeId));
    }

    @Override
    public Single<List<Step>> getStepsOfRecipe(int recipeId) {
        return Single.fromCallable(() -> mRecipesDao.getSteps(recipeId));
    }

    @Override
    public void setRecipes(List<Recipe> recipes) {
        mRecipesDao.deleteRecipes();
        mRecipesDao.insertRecipes(recipes);

        for (Recipe recipe : recipes){
            for (int i = 0; i < recipe.getIngredients().size(); i++){
                recipe.getIngredients().get(i).setRecipeId(recipe.getId());
            }

            for (int i = 0; i < recipe.getSteps().size(); i++){
                recipe.getSteps().get(i).setRecipeId(recipe.getId());
            }

            mRecipesDao.insertIngredients(recipe.getIngredients());
            mRecipesDao.insertSteps(recipe.getSteps());
        }
    }
}
