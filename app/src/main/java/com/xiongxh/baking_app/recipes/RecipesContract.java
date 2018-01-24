package com.xiongxh.baking_app.recipes;

import com.xiongxh.baking_app.base.BasePresenter;
import com.xiongxh.baking_app.base.BaseView;
import com.xiongxh.baking_app.data.bean.Recipe;

import java.util.List;

public interface RecipesContract {
    interface View extends BaseView<Presenter> {
        void showRecipeList(List<Recipe> recipeList);
        void showLoadingIndicator(boolean show);
        void showLoadingRecipesErrorMessage(String error);
        void showLoadingRecipesCompletedMessage();
        void showRecipeDetails(long recipeId);

        Presenter getPresenter();
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecipes();
        void openRecipeDetails(int recipeId);

        void syncData();
    }
}

