package com.xiongxh.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.reactivex.Observable;

import com.xiongxh.bakingapp.R;
import com.xiongxh.bakingapp.models.Ingredient;

import java.util.List;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{

    private List<Ingredient> ingredients;

    public IngredientAdapter(){}

    public IngredientAdapter(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ingredient, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder(ingredients.get(position).getIngredient());
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        holder.mIngredientView.setText(stringBuilder.toString());
    }


    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView mIngredientView;
        public ViewHolder(View itemView){
            super(itemView);

            mIngredientView = (TextView) itemView.findViewById(R.id.ingredient);

        }
    }
}
