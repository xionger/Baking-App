package com.xiongxh.baking_app.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiongxh.baking_app.R;

public class RecipeDetailActivity extends AppCompatActivity {
    private static final String RECIPE_KEY = "RECIPIE_ID";

    RecipeDetailFragment mRecipeDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mRecipeDetailFragment = RecipeDetailFragment
                .newInstance(getIntent().getIntExtra(RECIPE_KEY, -1));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_recipe_container, mRecipeDetailFragment)
                .commit();
    }

}
