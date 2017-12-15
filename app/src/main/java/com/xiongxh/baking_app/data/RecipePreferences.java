package com.xiongxh.baking_app.data;


import android.content.Context;
import android.content.SharedPreferences;

public class RecipePreferences {
    private static final String PREF_NAME = "app_prefs";
    private static final String PREF_SYNCED = "app_synced";
    private static final String PREF_RECIPES = "app_recipes";
    private static final String PREF_CHOOSE = "chosen_recipe";

    private final SharedPreferences sharedPreferences;

    public RecipePreferences (Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setRecipesSynced(boolean synced){
        sharedPreferences.edit().putBoolean(PREF_SYNCED, synced).apply();
    }

    public boolean isRecipesSynced(){
        return sharedPreferences.getBoolean(PREF_SYNCED, false);
    }
}
