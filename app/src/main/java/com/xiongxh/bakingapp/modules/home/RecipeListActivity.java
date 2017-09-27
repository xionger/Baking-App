package com.xiongxh.bakingapp.modules.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiongxh.bakingapp.R;
import com.xiongxh.bakingapp.base.BaseActivity;
import com.xiongxh.bakingapp.di.component.DaggerRecipeComponent;
import com.xiongxh.bakingapp.di.module.RecipeModule;
import com.xiongxh.bakingapp.modules.home.adapter.RecipeAdapter;
import com.xiongxh.bakingapp.mvp.presenter.RecipePresenter;
import com.xiongxh.bakingapp.mvp.views.MainView;
import com.xiongxh.bakingapp.utilities.NetworkUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class RecipeListActivity extends BaseActivity implements MainView {
    @BindView(R.id.recipe_list) protected RecyclerView mRecipeListRecycleView;

    @Inject
    protected RecipePresenter mRecipePresenter;

    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        loadRecipes();
    }

    private void initializeList(){
        mRecipeListRecycleView.setHasFixedSize(true);
        mRecipeListRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecipeAdapter = new RecipeAdapter();
        mRecipeListRecycleView.setAdapter(mRecipeAdapter);
    }

    private void loadRecipes(){
        if (NetworkUtils.isNetworkAvailable(this)){
            mRecipePresenter.getRecipes();
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
        DaggerRecipeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .recipeModule(new RecipeModule(this))
                .build().inject(this);
    }
}
