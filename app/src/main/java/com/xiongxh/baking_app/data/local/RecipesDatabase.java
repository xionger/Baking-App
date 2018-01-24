package com.xiongxh.baking_app.data.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

@Database(entities = {Recipe.class, Ingredient.class, Step.class}, version = 5)
public abstract class RecipesDatabase extends RoomDatabase {
    public abstract RecipesDao recipesDao();
}
