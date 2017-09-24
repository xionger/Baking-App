package com.xiongxh.bakingapp.Recipe;

import com.xiongxh.bakingapp.models.Recipe;

public interface BaseView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getRecipesSuccess(Recipe recipe);
}
