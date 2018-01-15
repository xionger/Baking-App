package com.xiongxh.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.RecipePreferences;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.local.RecipesDatabase;
import com.xiongxh.baking_app.data.local.RecipesDbContract;
import com.xiongxh.baking_app.data.local.RecipesLocalDataSource;
import com.xiongxh.baking_app.rx.RxScheduler;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

/**
 * Implementation of App Widget functionality.
 */
public class BakingIngredientWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, RecipesDatabase database, int recipeId) {

        RecipesLocalDataSource localDataSource = new RecipesLocalDataSource(database.recipesDao());

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_ingredient_widget);

        localDataSource.getRecipe(recipeId)
                .compose(RxScheduler.applySchedulersSingle())
                .subscribeWith(new DisposableSingleObserver<Recipe>() {
                    @Override
                    public void onSuccess(@NonNull Recipe recipe) {
                        views.setTextViewText(R.id.appwidget_text, recipe.getName());

                        Intent intent = new Intent(context, IngredientWidgetService.class);
                        intent.putExtra(IngredientWidgetService.INGREDIENTS_KEY, new Gson().toJson(recipe));

                        views.setRemoteAdapter(R.id.appwidget_list, intent);

                        appWidgetManager.updateAppWidget(appWidgetId, views);
                        dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e);
                        dispose();
                    }
                });
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        RecipesDatabase database = Room
                .databaseBuilder(context, RecipesDatabase.class, RecipesDbContract.DATABASE_NAME)
                .build();

        RecipePreferences widgetPrefs = new RecipePreferences(context, RecipePreferences.PREF_WIDGET);

        int recipeId;

        for (int appWidgetId : appWidgetIds) {
            recipeId = widgetPrefs.getWidet(appWidgetId);
            updateAppWidget(context, appWidgetManager, appWidgetId, database, recipeId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

