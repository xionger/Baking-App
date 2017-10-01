package com.xiongxh.bakingapp.recipes;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiongxh.bakingapp.R;
import com.xiongxh.bakingapp.utilities.ActivityUtils;

import butterknife.internal.Utils;

public class RecipesActivity extends AppCompatActivity{

    private static final String LOG_TAG = RecipesActivity.class.getSimpleName();

    RecipesPresenter mRecipesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_recipe);

        RecipesFragment recipesFragment = (RecipesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.recipes_fragment_container);

        if (recipesFragment == null) {
            recipesFragment = RecipesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), recipesFragment, R.id.recipes_fragment_container);
        }
    }
}
