package com.xiongxh.baking_app.api;

import com.xiongxh.baking_app.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RecipeApiService {
    private static Retrofit retrofit;

    public static void initRetrofit(OkHttpClient client){
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService initService(){
        ApiService apiService = retrofit.create(ApiService.class);

        return apiService;
    }
}
