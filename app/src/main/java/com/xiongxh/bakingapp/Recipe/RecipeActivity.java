package com.xiongxh.bakingapp.Recipe;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.xiongxh.bakingapp.BaseApp;
import com.xiongxh.bakingapp.models.Recipe;
import com.xiongxh.bakingapp.networks.RecipeService;

import javax.inject.Inject;

public class RecipeActivity extends BaseApp implements BaseView {

    private RecyclerView mRecipesRecycleView;

    @Inject
    public RecipeService recipeService;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getRecipesSuccess(Recipe recipe) {

    }
}
