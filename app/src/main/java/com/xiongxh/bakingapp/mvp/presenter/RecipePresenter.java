package com.xiongxh.bakingapp.mvp.presenter;

import com.xiongxh.bakingapp.api.RecipeApiService;
import com.xiongxh.bakingapp.base.BasePresenter;
import com.xiongxh.bakingapp.mvp.models.Recipe;
import com.xiongxh.bakingapp.mvp.views.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RecipePresenter extends BasePresenter<MainView> implements Observer<List<Recipe>> {

    @Inject protected RecipeApiService mRecipeApiService;
    @Inject
    public RecipePresenter(){}

    public void getRecipes(){
        //getView().onShowDialog("Loading recipes....");
        Observable<List<Recipe>> recipesObservable = mRecipeApiService.getRecipeList();
        subscribe(recipesObservable, this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull List<Recipe> recipes) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
