package com.xiongxh.baking_app.recipes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipesFragment extends Fragment implements RecipesContract.View{

    private static final String LOG_TAG = RecipesFragment.class.getSimpleName();

    private static final String SAVED_SCROLL_POSITION = "SAVED_SCROLL_POSITION";

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

        //mRecipesAdapter = new RecipesAdapter(new ArrayList<>(0),
        //        recipeId -> mRecipesPresenter.openRecipeDetails(recipeId));
        mRecipesAdapter = new RecipesAdapter();

        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecipesRecyclerView.setLayoutManager(mLayoutManager);

        mRecipesRecyclerView.setAdapter(mRecipesAdapter);

        mRecipesPresenter = new RecipesPresenter(this);

        return rootView;
    }

    /*
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_SCROLL_POSITION)){
            int position = savedInstanceState.getInt(SAVED_SCROLL_POSITION);
            //new Handler.postDelayed()
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setPresenter(RecipesContract.Presenter presenter) {
        this.mRecipesPresenter = presenter;
    }
*/
    @Override
    public void onResume(){
        super.onResume();
        mRecipesPresenter.subscribe(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        mRecipesPresenter.unsubscribe();
    }

    @Override
    public void showRecipeList(List<Recipe> recipeList) {
        mRecipesAdapter.refreshRecipes(recipeList);
    }

    @Override
    public void showRecipeDetails(long recipeId) {

    }

    @Override
    public void showLoadingIndicator(boolean show) {

    }

    @Override
    public void showLoadingRecipesErrorMessage(String error) {

    }

    @Override
    public void showLoadingRecipesCompletedMessage() {

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mRecipesPresenter.unsubscribe();
        unbinder.unbind();
    }
}