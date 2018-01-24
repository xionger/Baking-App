package com.xiongxh.baking_app.recipes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{

    private List<Recipe> mRecipes = new ArrayList<>();

    private OnRecipeClickListener mRecipeClickListener;

    public interface OnRecipeClickListener{
        void onClick(Recipe recipe);
    }

    public RecipesAdapter(OnRecipeClickListener recipeClickListener) {
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
        holder.itemView.setOnClickListener(view -> mRecipeClickListener.onClick(recipe));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public void refreshRecipes(List<Recipe> recipes){
        setRecipes(recipes);
        notifyDataSetChanged();
    }

    private void setRecipes(@NonNull List<Recipe> recipes){
        this.mRecipes = recipes;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final String LOG_TAG = ViewHolder.class.getCanonicalName();

        @BindView(R.id.tv_count_servings) TextView mServingsView;
        @BindView(R.id.tv_recipe_title) TextView mNameView;
        @BindView(R.id.iv_recipe_image) ImageView mImageView;
        @BindView(R.id.tv_count_steps) TextView mStepsCountView;

        private Recipe mRecipe;
        private int mRecipeId;

        public ViewHolder (View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
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