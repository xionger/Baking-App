package com.xiongxh.baking_app.recipesteps;

import com.xiongxh.baking_app.base.BasePresenter;
import com.xiongxh.baking_app.base.BaseView;
import com.xiongxh.baking_app.data.bean.Step;

import java.util.List;

public interface RecipeStepsContract {
    interface View extends BaseView<Presenter>{
        void showErrorMessage(String message);
        void showRecipeName(String recipeName);
        void showStepsInPager(List<Step>steps);
        void moveToCurrentStepPage();
    }

    interface Presenter extends BasePresenter<View>{
        void loadSteps();
        void setRecipeId(int recipeId);
    }
}
