package com.xiongxh.baking_app.data;

import com.xiongxh.baking_app.data.models.Ingredient;
import com.xiongxh.baking_app.data.models.Recipe;
import com.xiongxh.baking_app.data.models.Step;

import java.util.List;

import io.reactivex.Observable;

public interface DataService {
    Observable<List<Recipe>> getRecipes();
    Observable<List<Ingredient>> getIngredientsById(int recipeId);
    Observable<List<Ingredient>> getIngredientsByName(String recipeName);
    Observable<List<Step>> getSteps(int recipeId);

    void saveRecipes(List<Recipe> recipes);
}