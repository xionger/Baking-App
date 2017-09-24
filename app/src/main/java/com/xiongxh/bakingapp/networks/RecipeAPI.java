package com.xiongxh.bakingapp.networks;


import com.xiongxh.bakingapp.models.Recipe;

import io.reactivex.Observable;

import retrofit2.http.GET;

public interface RecipeAPI {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<Recipe> getRecipeList();
}
