package com.xiongxh.baking_app.recipes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xiongxh.baking_app.IdlingResource.RecipesIdlingResource;
import com.xiongxh.baking_app.R;

import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT_RECIPES = "TAG_FRAGMENT_RECIPES";

    private RecipesContract.View mRecipesFragment;

    @Nullable
    private RecipesIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_recipe);

        ButterKnife.bind(this);

            mRecipesFragment = RecipesFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipes_fragment_container, (Fragment)mRecipesFragment, TAG_FRAGMENT_RECIPES)
                    .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =  item.getItemId();

        if (id == R.id.action_refresh){
            mRecipesFragment.getPresenter().syncData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new RecipesIdlingResource();
        }
        return idlingResource;
    }

}
