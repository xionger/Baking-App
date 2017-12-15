package com.xiongxh.baking_app.api;


import com.xiongxh.baking_app.data.bean.Recipe;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Single<List<Recipe>> fetchRecipesFromServer();
}
