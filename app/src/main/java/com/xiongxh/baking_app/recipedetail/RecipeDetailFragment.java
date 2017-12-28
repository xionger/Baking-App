package com.xiongxh.baking_app.recipedetail;

import android.graphics.Typeface;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;
import com.xiongxh.baking_app.recipes.RecipesContract;
import com.xiongxh.baking_app.recipesteps.RecipeStepsActivity;
import com.xiongxh.baking_app.utils.IngredientsFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class RecipeDetailFragment extends Fragment implements RecipeDetailContract.View{

    private static final String LOG_TAG = RecipeDetailFragment.class.getSimpleName();

    private static final String RECIPE_ID_KEY = "RECIPIE_ID";
    private int mRecipeId;
    private RecipeDetailContract.Presenter mRecipeDetailPresenter;
    private RecipeDetailAdapter mRecipeDetailAdapter;
    private Unbinder unbinder;

    @BindView(R.id.tv_recipe_detail_ingredients)
    TextView mIngredientsView;

    @BindView(R.id.rv_steps_recipe)
    RecyclerView mStepsRecyclerView;

    @BindString(R.string.details_ingredients_header)
    String ingredientsListHeader;

    public RecipeDetailFragment(){}

    public static RecipeDetailFragment newInstance(int recipeId){
        Bundle args = new Bundle();
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        args.putInt(RECIPE_ID_KEY, recipeId);
        recipeDetailFragment.setArguments(args);

        return recipeDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        mRecipeDetailAdapter = new RecipeDetailAdapter(new ArrayList<>(0),
                stepId -> mRecipeDetailPresenter.openStepDetails(stepId));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mStepsRecyclerView.setLayoutManager(layoutManager);
        mStepsRecyclerView.setHasFixedSize(true);
        mStepsRecyclerView.setAdapter(mRecipeDetailAdapter);

        mStepsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                        DividerItemDecoration.VERTICAL));

        Log.d(LOG_TAG, "Before returning rootview of details ....");

        return rootView;
    }

    @Override
    public void onActivityCreated(@NonNull Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "Entering onActivityCreated...");
        mRecipeId = getArguments().getInt(RECIPE_ID_KEY);
        mRecipeDetailPresenter = new RecipeDetailPresenter(this, mRecipeId);
        Log.d(LOG_TAG, "Exiting onActivityCreated...");
    }

    @Override
    public void onResume(){
        super.onResume();
        mRecipeDetailPresenter.subscribe(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        mRecipeDetailPresenter.unsubscribe();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {
        Log.d(LOG_TAG, "Recipe name: " + recipe.getName());
    }


    @Override
    public void showRecipeName(String recipeName) {
        getActivity().setTitle(recipeName);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredientList) {
        StringBuilder sb = new StringBuilder();
        sb.append(ingredientsListHeader);

        for (Ingredient ingredient : ingredientList) {

            String name = ingredient.getIngredient();
            double quantity = ingredient.getQuantity();
            String measure = ingredient.getMeasure();

            sb.append("\n");

            sb.append(IngredientsFormatUtils.formatIngredient(getContext(), name, quantity, measure));

            //sb.append(ingredient.toString());
        }
        Timber.d(sb.toString());
        /*
        IngredientsFormatUtils.setTextWithSpan(mIngredientsView,
                sb.toString(),
                ingredientsListHeader,
                new StyleSpan(Typeface.BOLD));

        //Log.d(LOG_TAG, "Recipe ingredients: " + sb.toString());
        */
        mIngredientsView.setText(sb.toString());
    }

    @Override
    public void showSteps(List<Step> stepList) {
        mRecipeDetailAdapter.refreshSteps(stepList);
    }

    @Override
    public void showStepDetails(int stepId){
        startActivity(RecipeStepsActivity.prepareIntent(getContext(), mRecipeId, stepId));
    }

    @Override
    public void showMessage(String message) {

    }
}