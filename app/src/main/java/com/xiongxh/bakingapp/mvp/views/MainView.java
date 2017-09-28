package com.xiongxh.bakingapp.mvp.views;

import com.xiongxh.bakingapp.base.BaseView;
import com.xiongxh.bakingapp.mvp.models.Recipe;

import java.util.List;

public interface MainView extends BaseView {
    void onRecipesLoaded(List<Recipe> recipes);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
