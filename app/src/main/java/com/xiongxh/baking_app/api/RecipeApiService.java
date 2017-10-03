package com.xiongxh.baking_app.api;

import com.xiongxh.baking_app.data.models.Recipe;

import java.util.List;

import retrofit2.http.GET;
import io.reactivex.Observable;


public interface RecipeApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();
}
