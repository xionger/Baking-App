package com.xiongxh.bakingapp.mapper;

import com.xiongxh.bakingapp.models.Recipe;

import java.util.List;

import javax.inject.Inject;

public class RecipeMapper {

    @Inject
    public RecipeMapper(){}

    public List<Recipe> mapRecipes(List<Recipe> recipes){
        return recipes;
    }
}
