package com.xiongxh.bakingapp.mvp.presenter;

import android.util.Log;

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

    private final String LOG_TAG = RecipePresenter.class.getSimpleName();

    @Inject protected RecipeApiService mRecipeApiService;
    @Inject
    public RecipePresenter(){}

    public void getRecipes(){
        Log.d(LOG_TAG, "Entering getRecipes()...");
        getView().onShowDialog("Loading recipes....");
        Observable<List<Recipe>> recipesObservable = mRecipeApiService.getRecipeList();
        subscribe(recipesObservable, this);
        Log.d(LOG_TAG, "Exit getRecipes()...");
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.d(LOG_TAG, "Entering onSubscribe()...");
    }


    @Override
    public void onNext(@NonNull List<Recipe> recipes) {
        Log.d(LOG_TAG, "Entering onNext()...");

    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.d(LOG_TAG, "Entering onError()...");
    }

    @Override
    public void onComplete() {
        Log.d(LOG_TAG, "Entering onComplete()...");
    }
}
