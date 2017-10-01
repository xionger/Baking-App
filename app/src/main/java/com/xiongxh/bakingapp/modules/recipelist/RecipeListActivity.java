package com.xiongxh.bakingapp.modules.recipelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiongxh.bakingapp.R;

import com.xiongxh.bakingapp.application.BakingApp;
import com.xiongxh.bakingapp.mvp.presenter.RecipeListPresenter;

import javax.inject.Inject;


public class RecipeListActivity extends AppCompatActivity {
    private static final String LOG_TAG = RecipeListActivity.class.getSimpleName();

    @Inject
    RecipeListPresenter mRecipeListPresenter;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        setContentView(R.layout.activity_list_recipe);

        RecipeListFragment recipeListFragment = (RecipeListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.recipeListFragmentContainer);

        if (recipeListFragment == null) {
            recipeListFragment = RecipeListFragment.newInstance();
            FragmentUtils.addFragment(getSupportFragmentManager(), recipeListFragment, R.id.recipeListFragmentContainer);
        }

        ((BakingApp) getApplication()).getAppcomponent().inject(this);
    }
}
