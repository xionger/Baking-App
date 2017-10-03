package com.xiongxh.baking_app.data;

import com.xiongxh.baking_app.data.models.Ingredient;
import com.xiongxh.baking_app.data.models.Recipe;
import com.xiongxh.baking_app.data.models.Step;
import com.xiongxh.baking_app.data.remote.Remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class RecipesRepository implements DataService {

    private final DataService mRemoteRecipesDataService;


    @Inject
    public RecipesRepository(@Remote DataService remoteRecipesService){
        this.mRemoteRecipesDataService = remoteRecipesService;


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