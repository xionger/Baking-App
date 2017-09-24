package com.xiongxh.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiongxh.bakingapp.R;
import com.xiongxh.bakingapp.models.Recipe;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{
    private List<Recipe> recipes;

    public RecipeAdapter(){}

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.setRecipe(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mServingsView, mStepsCountView, mNameView;
        RecyclerView mIngredientsView;

        private Recipe recipe;

        public ViewHolder (View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mServingsView = (TextView) itemView.findViewById(R.id.tv_servings);
            mStepsCountView = (TextView) itemView.findViewById(R.id.tv_count_steps);
            mNameView = (TextView) itemView.findViewById(R.id.tv_name);
            mIngredientsView = (RecyclerView) itemView.findViewById(R.id.rv_ingredients);

            mIngredientsView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }

        @Override
        public void onClick(View view) {

        }

        public void setRecipe(Recipe recipe){
            this.recipe = recipe;
            mNameView.setText(recipe.getName());
            mServingsView.setText(recipe.getServings());
            mStepsCountView.setText(recipe.getSteps().size());

            IngredientAdapter ingredientAdapter = new IngredientAdapter(recipe.getIngredients());
            mIngredientsView.setAdapter(ingredientAdapter);
        }
    }


}
