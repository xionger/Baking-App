package com.xiongxh.baking_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.models.Recipe;
import com.xiongxh.baking_app.recipedetail.RecipeDetailActivity;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{

    private final String LOG_TAG = RecipesAdapter.class.getSimpleName();
    private List<Recipe> mRecipes;
    final OnRecipeClickListener mRecipeClickListener;

    interface OnRecipeClickListener{
        void recipeClicked(int recipeId);
    }

    RecipesAdapter(List<Recipe> recipes, OnRecipeClickListener recipeClickListener){
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
        holder.setRecipe(recipe);
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
        TextView mServingsView, mStepsCountView, mNameView;
        RecyclerView mIngredientsView;

        private Recipe mRecipe;

        public ViewHolder (View itemView){
            super(itemView);
            Log.d(LOG_TAG, "Entering ViewHolder constructor...");
            itemView.setOnClickListener(this);

            mServingsView = (TextView) itemView.findViewById(R.id.tv_count_servings);
            mStepsCountView = (TextView) itemView.findViewById(R.id.tv_count_steps);
            //mNameView = (TextView) itemView.findViewById(R.id.tv_name);
            mIngredientsView = (RecyclerView) itemView.findViewById(R.id.rv_ingredients);

            mIngredientsView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("recipe", mRecipe);
            context.startActivity(intent);
        }

        public void setRecipe(Recipe recipe){
            this.mRecipe = recipe;
            mNameView.setText(recipe.getName());
            mServingsView.setText(recipe.getServings());
            mStepsCountView.setText(recipe.getSteps().size());

            IngredientAdapter ingredientAdapter = new IngredientAdapter(recipe.getIngredients());
            mIngredientsView.setAdapter(ingredientAdapter);
        }
    }

}