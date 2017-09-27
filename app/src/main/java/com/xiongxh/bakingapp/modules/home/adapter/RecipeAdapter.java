package com.xiongxh.bakingapp.modules.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiongxh.bakingapp.R;
import com.xiongxh.bakingapp.modules.details.RecipeDetailActivity;
import com.xiongxh.bakingapp.mvp.models.Recipe;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{
    private List<Recipe> mRecipes;

    private Recipe mRecipe;

    public RecipeAdapter(){}

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recipe, parent, false);
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

    public void setRecipes(List<Recipe> recipes){
        this.mRecipes = recipes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mServingsView, mStepsCountView, mNameView;
        RecyclerView mIngredientsView;

        private Recipe mRecipe;

        public ViewHolder (View itemView){
            super(itemView);
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
