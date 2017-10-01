package com.xiongxh.bakingapp.modules.recipelist;

import android.support.v4.app.Fragment;

import com.xiongxh.bakingapp.models.Recipe;
import com.xiongxh.bakingapp.mvp.views.RecipeListViewContract;

import java.util.List;

public class RecipeListFragment extends Fragment implements RecipeListViewContract.View {
    private static final String LOG_TAG = RecipeListFragment.class.getSimpleName();

    @Override
    public void setPresenter(RecipeListViewContract.Presenter presenter) {

    }

    @Override
    public void showRecipeList(List<Recipe> recipeList) {

    }

    @Override
    public void showRecipeDetails(int recipeId) {

    }

    @Override
    public void showLoadingIndicator(boolean show) {

    }

    @Override
    public void showError() {

    }
}
