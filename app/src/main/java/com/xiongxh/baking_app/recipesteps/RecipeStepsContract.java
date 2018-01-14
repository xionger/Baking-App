package com.xiongxh.baking_app.recipesteps;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.xiongxh.baking_app.base.BasePresenter;
import com.xiongxh.baking_app.base.BaseView;
import com.xiongxh.baking_app.data.bean.Step;

public interface RecipeStepsContract {
    interface View extends BaseView<Presenter>{
        void showStep(Step step, SimpleExoPlayer player);
        void setStepNumber(String stepNumber);
        void showErrorMessage(String message);
        void setBackButton(boolean existed);
        void setNextButton(boolean existed);
        void showRecipeName(String recipeName);
    }

    interface Presenter extends BasePresenter<View>{
        int getCurrentIndex();
        void showStep(Step step);
        void loadRecipeDetails();
        void showNextStep();
        void showPreviousStep();
    }
}
