package com.xiongxh.bakingapp.data;


import com.xiongxh.bakingapp.models.Ingredient;
import com.xiongxh.bakingapp.models.Recipe;
import com.xiongxh.bakingapp.models.Step;

import java.util.List;

import io.reactivex.Observable;

public interface DataService {
    Observable<List<Recipe>> getRecipes();
    Observable<List<Ingredient>> getIngredientsById(int recipeId);
    Observable<List<Ingredient>> getIngredientsByName(String recipeName);
    Observable<List<Step>> getSteps(int recipeId);

    void saveRecipes(List<Recipe> recipes);
}
