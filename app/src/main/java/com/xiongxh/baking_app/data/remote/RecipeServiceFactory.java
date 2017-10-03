package com.xiongxh.baking_app.data.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiongxh.baking_app.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeServiceFactory {
    public static <T> T createFrom(Class<T> service, String url){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        /*
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(RecipesAdapterFactory.create())
                .create();
        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(service);
    }
}
