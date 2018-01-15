package com.xiongxh.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.Interactor.RecipesInteractor;
import com.xiongxh.baking_app.data.RecipePreferences;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.local.RecipesDatabase;
import com.xiongxh.baking_app.data.local.RecipesDbContract;
import com.xiongxh.baking_app.recipes.RecipesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class IngredientWidgetActivity extends AppCompatActivity {

    @BindView(R.id.rv_widget_ingredient)
    RecyclerView mIngredientRecyclerView;

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);
        setContentView(R.layout.activity_ingredient_widget);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null){
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID){
            finish();
            return;
        }

        mIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecipesAdapter recipesAdapter =
                new RecipesAdapter(new ArrayList<>(), new RecipesAdapter.OnRecipeClickListener() {
            @Override
            public void recipeClicked(int recipeId) {
                BakingApp.get().recipePreferences.setWidget(mAppWidgetId, recipeId);

                final Context context = IngredientWidgetActivity.this;
                RecipesDatabase database = Room.
                        databaseBuilder(context, RecipesDatabase.class, RecipesDbContract.DATABASE_NAME)
                        .build();

                RecipePreferences widgetPrefs =
                        new RecipePreferences(context, RecipePreferences.PREF_WIDGET);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                BakingIngredientWidget
                        .updateAppWidget(context,
                                appWidgetManager,
                                mAppWidgetId,
                                database,
                                widgetPrefs.getWidet(mAppWidgetId));

                Intent result = new Intent();
                result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, result);
                finish();
            }
        });

        mIngredientRecyclerView.setAdapter(recipesAdapter);

        compositeDisposable
                .add(new RecipesInteractor()
                .getRecipes()
                .subscribeWith(new DisposableSingleObserver<List<Recipe>>(){
                    @Override
                    public void onSuccess(@NonNull List<Recipe> recipes){
                        recipesAdapter.refreshRecipes(recipes);
                    }
                    @Override
                    public void onError(@NonNull Throwable e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    protected void onPause(){
        super.onPause();
        compositeDisposable.clear();
    }
}
