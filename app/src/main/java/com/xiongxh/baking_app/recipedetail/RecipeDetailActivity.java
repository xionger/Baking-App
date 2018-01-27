package com.xiongxh.baking_app.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.R;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String RECIPE_ID_KEY = "RECIPIE_ID_KEY";

    RecipeDetailFragment mRecipeDetailFragment = null;
    int mRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mRecipeId = getIntent().getIntExtra(RECIPE_ID_KEY, -1);

        if (savedInstanceState == null) {
            mRecipeDetailFragment = RecipeDetailFragment.newInstance(mRecipeId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_recipe_container, mRecipeDetailFragment)
                    .commit();
        }

        RecipeDetailContract.Presenter presenter = BakingApp.get().presenterProvider.provideRecipeDetails();

        presenter.setRecipeId(mRecipeId);
        mRecipeDetailFragment.setPresenter(presenter);
    }

    public static void onStartActivity(Context context, int recipeId){
        context.startActivity(
                new Intent(context, RecipeDetailActivity.class)
                        .putExtra(RECIPE_ID_KEY, recipeId));
    }


}
