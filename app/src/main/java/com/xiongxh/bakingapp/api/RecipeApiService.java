package com.xiongxh.bakingapp.api;

import com.xiongxh.bakingapp.mvp.models.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;


public interface RecipeApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipeList();

}
