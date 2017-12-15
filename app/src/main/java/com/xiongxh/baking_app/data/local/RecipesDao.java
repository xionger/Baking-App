package com.xiongxh.baking_app.data.local;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

import java.util.List;

@Dao
public interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(List<Recipe> recipes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredients(List<Ingredient> ingredients);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSteps(List<Step> steps);

    @Query("SELECT * FROM " + RecipesDbContract.RECIPES_TABLE_NAME)
    List<Recipe> getRecipes();

    @Query("SELECT * FROM " + RecipesDbContract.RECIPES_TABLE_NAME + " WHERE id=:recipeId")
    Recipe getRecipe(int recipeId);

    @Query("SELECT * FROM " + RecipesDbContract.INGREDIENTS_TABLE_NAME + " WHERE recipeId=:recipeId")
    List<Ingredient> getIngredients(int recipeId);

    @Query("SELECT * FROM " + RecipesDbContract.STEPS_TABLE_NAME + " WHERE recipeId=:recipeId")
    List<Step> getSteps(int recipeId);

    @Query("DELETE FROM " + RecipesDbContract.RECIPES_TABLE_NAME)
    void deleteRecipes();

}
