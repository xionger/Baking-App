package com.xiongxh.baking_app.data;


import android.content.Context;
import android.content.SharedPreferences;

public class RecipePreferences {
    private static final String PREF_APP = "app_prefs";
    private static final String PREF_SYNCED = "app_synced";
    private static final String PREF_RECIPES = "app_recipes";
    private static final String PREF_CHOOSE = "chosen_recipe";

    private final SharedPreferences appPreferences;

    public RecipePreferences (Context context){
        appPreferences = context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE);
    }

    public void setRecipesSynced(boolean syncState){
        appPreferences.edit().putBoolean(PREF_SYNCED, syncState).apply();
    }

    public boolean isRecipesSynced(){
        return appPreferences.getBoolean(PREF_SYNCED, false);
    }
    //getBoolean(SYNC_KEY, appPrefs)
    //appPrefs.getBoolean(SYNC_KEY, false);
    /*
    private boolean getBoolean(String key, SharedPreferences preferences){
        return preferences.getBoolean(key, false);
    }*/

}
