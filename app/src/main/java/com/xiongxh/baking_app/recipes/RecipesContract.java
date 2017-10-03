package com.xiongxh.baking_app.recipes;

import com.xiongxh.baking_app.base.BasePresenter;
import com.xiongxh.baking_app.base.BaseView;
import com.xiongxh.baking_app.data.models.Recipe;

import java.util.List;

public interface RecipesContract {
    interface View extends BaseView<Presenter> {
        void showRecipeList(List<Recipe> recipeList);
        void showRecipeDetails(int recipeId);
        void showLoadingIndicator(boolean show);
        void showError();
    }

    interface Presenter extends BasePresenter {

    }
}

