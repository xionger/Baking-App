package com.xiongxh.bakingapp.recipes;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.xiongxh.bakingapp.R;
import com.xiongxh.bakingapp.adapters.RecipesAdapter;
import com.xiongxh.bakingapp.models.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.Unbinder;

public class RecipesFragment extends Fragment implements RecipesContract.View{

    private static final String LOG_TAG = RecipesFragment.class.getCanonicalName();

    @BindView(R.id.rv_list_recipe)
    RecyclerView mRecipesRecyclerView;

    @BindView(R.id.pb_list_recipe)
    ProgressBar mRecipesProgressbar;

    Unbinder unbinder;

    private RecipesContract.Presenter mRecipesPresenter;
    private RecipesAdapter mRecipesAdapter;

    public RecipesFragment(){}

    public static RecipesFragment newInstance(){
        return new RecipesFragment();
    }

    @Override
    public void setPresenter(RecipesContract.Presenter presenter) {

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
