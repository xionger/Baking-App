package com.xiongxh.baking_app.recipedetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.StyleSpan;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeDetailFragment extends Fragment implements RecipeDetailContract.View{

    private static final String RECIPE_ID_KEY = "RECIPIE_ID";
    private int mRecipeId;
    private RecipeDetailContract.Presenter mRecipeDetailPresenter;
    private RecipeDetailAdapter mRecipeDetailAdapter;
    private Unbinder unbinder;

    @BindView(R.id.tv_recipe_detail_ingredients)
    TextView mIngredientsView;

    @BindView(R.id.rv_steps_recipe)
    RecyclerView mStepsRecyclerView;

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

        mRecipeDetailAdapter = new RecipeDetailAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mStepsRecyclerView.setLayoutManager(layoutManager);
        mStepsRecyclerView.setHasFixedSize(true);
        mStepsRecyclerView.setAdapter(mRecipeDetailAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@NonNull Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mRecipeId = getArguments().getInt(RECIPE_ID_KEY);
        mRecipeDetailPresenter = new RecipeDetailPresenter(this, mRecipeId);
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {

    }

    @Override
    public void showIngredients(List<Ingredient> ingredientList) {
        StringBuilder sb = new StringBuilder();
        //sb.append(ingredientsListHeader);

        for (Ingredient ingredient : ingredientList) {
            sb.append("\n");
            /*
            String name = ingredient.getIngredient();
            double quantity = ingredient.getQuantity();
            String measure = ingredient.getMeasure();

            sb.append("\n");
            sb.append(StringUtils.formatIngdedient(getContext(), name, quantity, measure));
            */
            sb.append(ingredient.toString());
        }

        //TextViewUtils.setTextWithSpan(recipeDetailsIngredients, sb.toString(), ingredientsListHeader, new StyleSpan(Typeface.BOLD));
        mIngredientsView.setText(sb.toString());
    }

    @Override
    public void showSteps(List<Step> stepList) {
        mRecipeDetailAdapter.refreshSteps(stepList);
    }

    @Override
    public void showMessage(String message) {

    }
}