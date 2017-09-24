package com.xiongxh.bakingapp.modules.home;

import android.content.Intent;
import android.os.Bundle;

import com.xiongxh.bakingapp.base.BaseActivity;
import com.xiongxh.bakingapp.di.component.DaggerRecipeComponent;
import com.xiongxh.bakingapp.di.module.RecipeModule;
import com.xiongxh.bakingapp.mvp.views.MainView;


public class MainActivity extends BaseActivity implements MainView {

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        //initializeList();
        //loadCakes();
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void resolveDaggerDependency(){
        DaggerRecipeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .recipeModule(new RecipeModule(this))
                .build().inject(this);
    }
}
