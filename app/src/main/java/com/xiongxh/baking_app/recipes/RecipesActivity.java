package com.xiongxh.baking_app.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.base.BaseApp;
import com.xiongxh.baking_app.di.DaggerRecipesComponent;
import com.xiongxh.baking_app.di.RecipesModule;
import com.xiongxh.baking_app.utils.ActivityUtils;

import javax.inject.Inject;

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
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    recipesFragment, R.id.recipes_fragment_container);
        }

        //mRecipesPresenter = BaseApp.get().presenterProvider.provideRecipes();
        //recipesFragment.setPresenter(mRecipesPresenter);
        DaggerRecipesComponent.builder()
                .recipesRepositoryComponent(((BaseApp) getApplication()).getRecipesRepositoryComponent())
                .recipesModule(new RecipesModule(recipesFragment))
                .build()
                .inject(this);
    }
}
