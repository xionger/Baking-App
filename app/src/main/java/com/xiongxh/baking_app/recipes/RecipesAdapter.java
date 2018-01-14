package com.xiongxh.baking_app.recipes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.recipedetail.RecipeDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{

    private final String LOG_TAG = RecipesAdapter.class.getSimpleName();
    private static final String RECIPE_ID_KEY = "RECIPIE_ID";

    private List<Recipe> mRecipes = new ArrayList<>();

    final OnRecipeClickListener mRecipeClickListener;
    public interface OnRecipeClickListener{
        void recipeClicked(int recipeId);
    }

    public RecipesAdapter(List<Recipe> recipes, OnRecipeClickListener recipeClickListener){
        setRecipes(recipes);
        this.mRecipeClickListener = recipeClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_recipe, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        holder.bindRecipe(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    /*
    @Override
    public long getItemId(int position){
        return mRecipes.get(position).id();
    }
    */

    public void refreshRecipes(List<Recipe> recipes){
        setRecipes(recipes);
        notifyDataSetChanged();
    }

    private void setRecipes(@NonNull List<Recipe> recipes){
        this.mRecipes = recipes;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final String LOG_TAG = ViewHolder.class.getCanonicalName();
        //TextView mServingsView, mStepsCountView, mNameView;
        //RecyclerView mIngredientsView;

        @BindView(R.id.tv_count_servings) TextView mServingsView;
        @BindView(R.id.tv_recipe_title) TextView mNameView;
        @BindView(R.id.iv_recipe_image) ImageView mImageView;
        @BindView(R.id.tv_count_steps) TextView mStepsCountView;
        //@BindView(R.id.rv_ingredients) RecyclerView mIngredientsView;

        private Recipe mRecipe;
        private int mRecipeId;

        public ViewHolder (View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mRecipeClickListener.recipeClicked(mRecipeId);
            Context context = view.getContext();
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra(RECIPE_ID_KEY, mRecipeId);
            Log.d(LOG_TAG, "Recipe ID: " + mRecipeId);
            context.startActivity(intent);
        }

        public void bindRecipe(final Recipe recipe){
            this.mRecipe = recipe;
            this.mRecipeId = recipe.getId();

            mNameView.setText(recipe.getName());
            mServingsView.setText("Servings: " + String.valueOf(recipe.getServings()-1));
            mImageView.setImageResource(R.drawable.baking_logo);
            mStepsCountView.setText("Steps: " + String.valueOf(recipe.getSteps().size()-1));
        }
    }

}