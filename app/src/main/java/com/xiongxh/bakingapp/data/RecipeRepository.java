package com.xiongxh.bakingapp.data;

import com.xiongxh.bakingapp.models.Ingredient;
import com.xiongxh.bakingapp.models.Recipe;
import com.xiongxh.bakingapp.models.Step;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class RecipeRepository implements DataService {

    @Inject
    public RecipeRepository(){

    }

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
