package com.xiongxh.baking_app.recipes;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.RecipePreferences;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.recipedetail.RecipeDetailActivity;
import com.xiongxh.baking_app.utils.UiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipesFragment extends Fragment implements RecipesContract.View{

    @BindView(R.id.rv_list_recipe)
    RecyclerView mRecipesRecyclerView;

    @BindView(R.id.pb_list_recipe)
    ProgressBar mRecipesProgressbar;

    Unbinder unbinder;

    private RecipesContract.Presenter mRecipesPresenter;
    private RecipesAdapter mRecipesAdapter;
    private GridLayoutManager mLayoutManager;

    public RecipesFragment(){}

    public static RecipesFragment newInstance(){
        return new RecipesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_list_recipe, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        mRecipesAdapter = new RecipesAdapter(
                recipe -> RecipeDetailActivity.onStartActivity(getContext(), recipe.getId()));

        mLayoutManager = new GridLayoutManager(getContext(), UiUtils.getCoulumnNumber());
        mRecipesRecyclerView.setLayoutManager(mLayoutManager);

        mRecipesRecyclerView.setAdapter(mRecipesAdapter);

    }

    @Override
    public void onResume(){
        super.onResume();
        mRecipesPresenter.subscribe(this);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mRecipesPresenter.unsubscribe();
        unbinder.unbind();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void showRecipeList(List<Recipe> recipeList) {
        if (recipeList.size() <= 0){
            RecipePreferences recipePreferences = new RecipePreferences(getContext());
            recipePreferences.setRecipesSynced(false);
            mRecipesPresenter.subscribe(this);
        }
        mRecipesAdapter.refreshRecipes(recipeList);
    }

    @Override
    public void showRecipeDetails(long recipeId) {

    }

    @Override
    public void showLoadingIndicator(boolean show) {
        if (show){
            mRecipesRecyclerView.setVisibility(View.INVISIBLE);
            mRecipesProgressbar.setVisibility(View.VISIBLE);
        } else{
            mRecipesRecyclerView.setVisibility(View.VISIBLE);
            mRecipesProgressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showLoadingRecipesErrorMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingRecipesCompletedMessage() {
        final Snackbar snackbar = Snackbar
                .make(mRecipesRecyclerView, "Load Finished", Snackbar.LENGTH_LONG);

        snackbar.setActionTextColor(Color.MAGENTA)
                .setAction("Refresh", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refresh();
                    }
                });

        snackbar.show();
    }

    @Override
    public RecipesContract.Presenter getPresenter(){
        return mRecipesPresenter;
    }

    @Override
    public void setPresenter(RecipesContract.Presenter presenter){
        this.mRecipesPresenter = presenter;
    }

    public void refresh(){
        mRecipesPresenter.syncData();
    }
}