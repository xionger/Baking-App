package com.xiongxh.baking_app.recipedetail;

import com.xiongxh.baking_app.base.BasePresenter;
import com.xiongxh.baking_app.base.BaseView;
import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

import java.util.List;

public interface RecipeDetailContract {
    interface View extends BaseView<Presenter> {
        void showRecipeDetails(Recipe recipe);
        void showIngredients(List<Ingredient> ingredientList);
        void showSteps(List<Step> stepList);
        void showStepDetails(int stepId);
        void showRecipeName(String recipeName);
        void showErrorMessage(String message);

        void refreshStepContainer(String desc, String videoUrl, String imageThumbnailUrl);
    }

    interface Presenter extends BasePresenter<View>{
        void loadRecipeDetails();
        void openStepDetails(int stepId);
        void fetchStepData(int stepId);
        void setRecipeId(int recipeId);
    }
}
