package com.xiongxh.baking_app.recipedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;
import com.xiongxh.baking_app.recipesteps.RecipeStepsActivity;
import com.xiongxh.baking_app.recipesteps.RecipeStepsFragment;
import com.xiongxh.baking_app.recipesteps.StepPageFragment;
import com.xiongxh.baking_app.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class RecipeDetailFragment extends Fragment implements RecipeDetailContract.View{


    private int mRecipeId;
    private int mStepId = 0;
    private RecipeDetailContract.Presenter mRecipeDetailPresenter;
    private RecipeDetailAdapter mRecipeDetailAdapter;
    private LinearLayoutManager mLayoutManager;
    //private int currentVisiblePosition;
    private Unbinder unbinder;

    @BindView(R.id.rv_steps_recipe)
    RecyclerView mStepsRecyclerView;

    @BindView(R.id.layout_ingredients)
    LinearLayout mIngredientsLayout;

    public RecipeDetailFragment(){}

    public static RecipeDetailFragment newInstance(int recipeId){
        Bundle args = new Bundle();
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        args.putInt(RecipeDetailActivity.RECIPE_ID_KEY, recipeId);
        recipeDetailFragment.setArguments(args);

        return recipeDetailFragment;
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRecipeId = getArguments().getInt(RecipeDetailActivity.RECIPE_ID_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        mRecipeDetailAdapter = new RecipeDetailAdapter(new ArrayList<>(0),
                stepId -> mRecipeDetailPresenter.openStepDetails(stepId));

        mLayoutManager = new LinearLayoutManager(getContext());

        mStepsRecyclerView.setLayoutManager(mLayoutManager);
        mStepsRecyclerView.setHasFixedSize(true);
        mStepsRecyclerView.setAdapter(mRecipeDetailAdapter);

        mStepsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                        DividerItemDecoration.VERTICAL));

        if (UiUtils.isTablet()){
            mRecipeDetailPresenter.fetchStepData(mStepId);
        }

        return rootView;
    }

    /*
    @Override
    public void onActivityCreated(@NonNull Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mRecipeId = getArguments().getInt(RECIPE_ID_KEY);
        //mRecipeDetailPresenter = new RecipeDetailPresenter(this, mRecipeId);

        if (UiUtils.isTablet()){
            mRecipeDetailPresenter.fetchStepData(mStepId);
        }
    }
    */

    @Override
    public void onResume(){
        super.onResume();
        mRecipeDetailPresenter.subscribe(this);
        /*
        currentVisiblePosition = ((LinearLayoutManager)mStepsRecyclerView.getLayoutManager())
                .findFirstCompletelyVisibleItemPosition();
        currentVisiblePosition = 0;
        */
    }

    @Override
    public void onPause(){
        super.onPause();
        /*
        ((LinearLayoutManager)mStepsRecyclerView.getLayoutManager())
                .scrollToPosition(currentVisiblePosition);
                */
        mRecipeDetailPresenter.unsubscribe();

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {
        Timber.d("Recipe name: " + recipe.getName());
    }


    @Override
    public void showRecipeName(String recipeName) {
        getActivity().setTitle(recipeName);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredientList) {
        mIngredientsLayout.removeAllViews();
        for (Ingredient ingredient : ingredientList){
            IngredientsView ingredientsView = new IngredientsView(getContext());
            mIngredientsLayout.addView(ingredientsView.bind(ingredient));
        }
    }


    @Override
    public void showSteps(List<Step> stepList) {
        mRecipeDetailAdapter.refreshSteps(stepList);
    }

    @Override
    public void showStepDetails(int stepId){
        mStepId = stepId;

        if (UiUtils.isTablet()){
            mRecipeDetailPresenter.fetchStepData(stepId);
        } else {
            Timber.d("Open setep page, mRecipeId: " + mRecipeId + ", stepId: " + stepId);
            startActivity(RecipeStepsActivity.prepareIntent(getContext(), mRecipeId, stepId));
        }
    }

    /*
    @Override
    public void refreshStepContainer(int stepId) {
        mStepId = stepId;
        RecipeStepsFragment mRecipeStepsFragment = RecipeStepsFragment.newInstance(stepId);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.step_recipe_container, mRecipeStepsFragment)
                .commit();
    }
*/

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshStepContainer(String desc, String videoUrl, String imageThumbnailUrl) {
        Timber.d("step description: " + desc);
        StepPageFragment stepPageFragment =
                StepPageFragment.newInstance(desc, videoUrl, imageThumbnailUrl);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.step_recipe_container, stepPageFragment)
                .commit();
    }

    @Override
    public void setPresenter(RecipeDetailContract.Presenter presenter) {
        this.mRecipeDetailPresenter = presenter;
    }

    public class IngredientsView extends LinearLayout {
        @BindView(R.id.ingredient_name) TextView mIngredientNameView;
        @BindView(R.id.ingredient_quantity) TextView mIngredientQuantityView;
        @BindView(R.id.ingredient_measure) TextView mIngredientMeasure;

        public IngredientsView(Context context){
            super(context);

            View ingredientView = inflate(getContext(), R.layout.view_ingredient_item, this);
            ButterKnife.bind(ingredientView, this);
        }

        public View bind(Ingredient ingredient){
            mIngredientNameView.setText(ingredient.getIngredient());
            mIngredientQuantityView.setText(String.valueOf(ingredient.getQuantity()));
            mIngredientMeasure.setText(ingredient.getMeasure());

            return this;
        }
    }

}