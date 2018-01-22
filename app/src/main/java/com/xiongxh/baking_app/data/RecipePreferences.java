package com.xiongxh.baking_app.data;


import android.content.Context;
import android.content.SharedPreferences;

import timber.log.Timber;

public class RecipePreferences {
    public static final String PREF_APP = "app_prefs";
    public static final String PREF_WIDGET = "widget_prefs";

    private static final String PREF_SYNCED = "app_synced";
    private static final String WIDGET_PREFIX = "WIDGET_";

    private static final String PREF_RECIPES = "app_recipes";
    private static final String PREF_CHOOSE = "chosen_recipe";

    private SharedPreferences appPreferences;
    private SharedPreferences widgetPrefences;

    public RecipePreferences (Context context){
        appPreferences = context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE);
        widgetPrefences = context.getSharedPreferences(PREF_WIDGET, Context.MODE_PRIVATE);
    }

    public RecipePreferences(Context context, String pref){
        switch (pref){
            case PREF_APP:
                appPreferences = context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE);
                break;
            case PREF_WIDGET:
                Timber.d("case PREF_WIDGET.....");
                widgetPrefences = context.getSharedPreferences(PREF_WIDGET, Context.MODE_PRIVATE);
                break;
        }
    }

    public void setRecipesSynced(boolean syncState){
        Timber.d("Set recipes sync state as: " + syncState);
        appPreferences.edit().putBoolean(PREF_SYNCED, syncState).apply();
    }

    public boolean isRecipesSynced(){
        Timber.d("Check whether recipes are synced ...");
        boolean isSynced = appPreferences.getBoolean(PREF_SYNCED, false);
        Timber.d("Sync status is : " + isSynced);
        return isSynced;
        //return appPreferences.getBoolean(PREF_SYNCED, false);
    }
    //getBoolean(SYNC_KEY, appPrefs)
    //appPrefs.getBoolean(SYNC_KEY, false);
    /*
    private boolean getBoolean(String key, SharedPreferences preferences){
        return preferences.getBoolean(key, false);
    }*/

    public void setWidget(int widgetId, int recipeId){
        Timber.d("Entering setWidget, widgetId: " + widgetId + " , recipeId: " + recipeId);
        widgetPrefences.edit().putInt(WIDGET_PREFIX + widgetId, recipeId).apply();
    }

    public int getWidet(int widgetId){
        Timber.d("Entering getWidget, widgetId: " + widgetId);
        return widgetPrefences.getInt(WIDGET_PREFIX + widgetId, -1);
    }

}
