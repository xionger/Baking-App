package com.xiongxh.bakingapp.modules.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xiongxh.bakingapp.R;
import com.xiongxh.bakingapp.base.BaseActivity;
import com.xiongxh.bakingapp.di.component.DaggerRecipeComponent;
import com.xiongxh.bakingapp.di.module.RecipeModule;
import com.xiongxh.bakingapp.modules.home.adapter.RecipeAdapter;
import com.xiongxh.bakingapp.mvp.models.Recipe;
import com.xiongxh.bakingapp.mvp.presenter.RecipePresenter;
import com.xiongxh.bakingapp.mvp.views.MainView;
import com.xiongxh.bakingapp.utilities.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

public class RecipeListActivity extends BaseActivity implements MainView {

    private final String LOG_TAG = RecipeListActivity.class.getSimpleName();

    //@BindView(R.id.recipe_list) RecyclerView mRecipeListRecycleView;
    protected RecyclerView mRecipeListRecycleView;

    @Inject
    protected RecipePresenter mRecipePresenter;

    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        //getContentView();
        mRecipeListRecycleView = (RecyclerView) findViewById(R.id.recipe_list);
        initializeList();
        Log.d(LOG_TAG, "line 41...");
        loadRecipes();
        Log.d(LOG_TAG, "line 43...");
    }

    private void initializeList(){
        Log.d(LOG_TAG, "entering initializeList()...");
        mRecipeListRecycleView.setHasFixedSize(true);
        mRecipeListRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecipeAdapter = new RecipeAdapter();
        mRecipeListRecycleView.setAdapter(mRecipeAdapter);
        Log.d(LOG_TAG, "leaving initializeList()...");
    }

    private void loadRecipes(){
        if (NetworkUtils.isNetworkAvailable(this)){
            mRecipePresenter.getRecipes();
            Log.d(LOG_TAG, "mRecipePresenter.getRecipes()...");
        } else {
            //mRecipePresenter.getRecipesFromDatabase;
        }
    }

    @Override
    protected int getContentView() {

        return R.layout.activity_list_recipe;
    }

    @Override
    protected void resolveDaggerDependency(){
        Log.d(LOG_TAG, "entering resolveDaggerDependency()...");
        DaggerRecipeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .recipeModule(new RecipeModule(this))
                .build().inject(this);
    }

    @Override
    public void onRecipesLoaded(List<Recipe> recipes) {
        Log.d(LOG_TAG, "entering onRecipesLoaded...");
        mRecipeAdapter.setRecipes(recipes);
        Log.d(LOG_TAG, "leaving onRecipesLoaded...");
    }

    @Override
    public void onShowDialog(String message) {

    }

    @Override
    public void onHideDialog() {

    }

    @Override
    public void onShowToast(String message) {

    }

    @Override
    public void onClearItems() {

    }
}
