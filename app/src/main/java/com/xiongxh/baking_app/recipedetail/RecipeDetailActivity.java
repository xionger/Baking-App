package com.xiongxh.baking_app.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiongxh.baking_app.R;

public class RecipeDetailActivity extends AppCompatActivity {
    private static final String RECIPE_KEY = "RECIPIE_ID";

    RecipeDetailFragment mRecipeDetailFragment = null;

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
        }
    }

    public static void onStartActivity(Context context, int recipeId){
        context.startActivity(
                new Intent(context, RecipeDetailActivity.class)
                        .putExtra(RECIPE_KEY, recipeId));
    }

}
