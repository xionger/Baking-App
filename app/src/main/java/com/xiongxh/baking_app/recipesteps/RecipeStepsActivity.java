package com.xiongxh.baking_app.recipesteps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.R;

public class RecipeStepsActivity extends AppCompatActivity {
    private static final String RECIPE_ID_KEY = "RECIPIE_ID";
    private static final String STEP_ID_KEY = "STEP_ID";

    private static final String FRAGMENT_STEPS = "FRAGMENT_STEPS";

    int mRecipeId;

    RecipeStepsFragment mRecipeStepsFragment = null;

    public static Intent prepareIntent(Context context, int recipeId, int stepId){
        Intent intent = new Intent(context, RecipeStepsActivity.class);
        intent.putExtra(RECIPE_ID_KEY, recipeId);
        intent.putExtra(STEP_ID_KEY, stepId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        mRecipeId = getIntent().getIntExtra(RECIPE_ID_KEY, -1);

        if (savedInstanceState == null){
            int stepId = getIntent().getIntExtra(STEP_ID_KEY, -1);
            mRecipeStepsFragment = RecipeStepsFragment.newInstance(stepId);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.step_recipe_container, mRecipeStepsFragment, FRAGMENT_STEPS)
                    .commit();
        }

        if (mRecipeStepsFragment == null){
            mRecipeStepsFragment =
                    (RecipeStepsFragment) getSupportFragmentManager()
                            .findFragmentByTag(FRAGMENT_STEPS);

        }

        RecipeStepsContract.Presenter presenter = BakingApp.get().presenterProvider.provideSteps();

        presenter.setRecipeId(mRecipeId);
        mRecipeStepsFragment.setPresenter(presenter);
    }
}
