package com.xiongxh.bakingapp.models;


import com.google.gson.annotations.Expose;

import java.util.List;

public class Recipes {
    @Expose
    private List<Recipe> recipes;

    public Recipes(){}

    public List<Recipe> getRecipes(){
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
