package com.xiongxh.baking_app.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.recipesteps.RecipeStepsFragment;
import com.xiongxh.baking_app.utils.UiUtils;

public class RecipeDetailActivity extends AppCompatActivity {
    private static final String RECIPE_KEY = "RECIPIE_ID";
    private static final String FRAGMENT_RECIPEDETAIL = "FRAGMENT_RECIPEDETAIL";
    private static final String FRAGMENT_STEPS = "FRAGMENT_STEPS";

    RecipeDetailFragment mRecipeDetailFragment = null;
    RecipeStepsFragment mRecipeStepsFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        int recipeId = getIntent().getIntExtra(RECIPE_KEY, -1);

        if (savedInstanceState == null) {
            mRecipeDetailFragment = RecipeDetailFragment.newInstance(recipeId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_recipe_container, mRecipeDetailFragment)
                    .commit();

/*
            if (UiUtils.isTablet()){
                mRecipeStepsFragment = RecipeStepsFragment.newInstance(recipeId, 0);
            }

            if (mRecipeStepsFragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.step_recipe_container, mRecipeStepsFragment)
                        .commit();
            }
*/
        }
    }

    public static void onStartActivity(Context context, int recipeId){
        context.startActivity(
                new Intent(context, RecipeDetailActivity.class)
                        .putExtra(RECIPE_KEY, recipeId));
    }

}
