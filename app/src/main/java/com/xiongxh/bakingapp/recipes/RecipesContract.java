package com.xiongxh.bakingapp.recipes;

import com.xiongxh.bakingapp.base.BasePresenter;
import com.xiongxh.bakingapp.base.BaseView;
import com.xiongxh.bakingapp.models.Recipe;

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
