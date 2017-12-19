package com.xiongxh.baking_app;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.leakcanary.LeakCanary;
import com.xiongxh.baking_app.api.RecipeApiService;
import com.xiongxh.baking_app.base.PresenterProvider;
import com.xiongxh.baking_app.data.RecipePreferences;
import com.xiongxh.baking_app.data.local.RecipesDatabase;
import com.xiongxh.baking_app.data.local.RecipesDbContract;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class BakingApp extends Application{

    private static BakingApp INSTANCE;
    public OkHttpClient client = new OkHttpClient();

    public RecipesDatabase database;
    public PresenterProvider presenterProvider;
    public RecipePreferences recipePreferences;

    public static BakingApp get(){ return INSTANCE; }

    @Override
    public void onCreate(){
        super.onCreate();

        /*
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }

        LeakCanary.install(this);
        */
        INSTANCE = this;

        Timber.plant(new Timber.DebugTree());
        Stetho.initializeWithDefaults(this);
        client = new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();
        RecipeApiService.initRetrofit(client);

        database = Room.databaseBuilder(this,
                RecipesDatabase.class,
                RecipesDbContract.DATABASE_NAME)
                .build();

        recipePreferences = new RecipePreferences(this);

        //presenterProvider = new PresenterProvider();
    }

}
