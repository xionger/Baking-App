package com.xiongxh.bakingapp.data;


import com.xiongxh.bakingapp.mvp.models.Ingredient;
import com.xiongxh.bakingapp.mvp.models.Recipe;
import com.xiongxh.bakingapp.mvp.models.Step;

import java.util.List;

import io.reactivex.Observable;

public class BakingRepository implements DataService{
    @Override
    public Observable<List<Recipe>> getRecipes() {
        return null;
    }

    @Override
    public Observable<List<Ingredient>> getIngredientsById(int recipeId) {
        return null;
    }

    @Override
    public Observable<List<Ingredient>> getIngredientsByName(String recipeName) {
        return null;
    }

    @Override
    public Observable<List<Step>> getSteps(int recipeId) {
        return null;
    }

    @Override
    public void saveRecipes(List<Recipe> recipes) {

    }
}
