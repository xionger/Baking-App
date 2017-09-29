package com.xiongxh.bakingapp.mvp.views;


import com.xiongxh.bakingapp.base.BasePresenter;
import com.xiongxh.bakingapp.base.BaseView;
import com.xiongxh.bakingapp.mvp.models.Recipe;

import java.util.List;

public class RecipeListViewContract {
    public interface View extends BaseView<Presenter>{
        void showRecipeList(List<Recipe> recipeList);
        void showRecipeDetails(int recipeId);
        void showLoadingIndicator(boolean show);
        void showError();
    }

    public interface Presenter extends BasePresenter{

    }
}
