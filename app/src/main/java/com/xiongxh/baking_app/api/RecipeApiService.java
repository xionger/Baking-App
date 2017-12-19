package com.xiongxh.baking_app.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RecipeApiService {
    private static Retrofit retrofit;

    private static final String API_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public static void initRetrofit(OkHttpClient client){
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiService initService(){
        ApiService apiService = retrofit.create(ApiService.class);

        return apiService;
    }

}
