package com.xiongxh.baking_app.recipes;

import android.support.annotation.NonNull;

import com.xiongxh.baking_app.data.RecipesRepository;
import com.xiongxh.baking_app.scheduler.BaseSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class RecipesPresenter implements RecipesContract.Presenter{

    @NonNull
    private final RecipesRepository mRecipeRepository;
    @NonNull
    private final RecipesContract.View mRecipesView;
    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeDisposable mDisposableRecipes;

    public RecipesPresenter(@NonNull RecipesRepository recipesRepository,
                            @NonNull RecipesContract.View recipesView,
                            @NonNull BaseSchedulerProvider schedulerProvider){
        this.mRecipeRepository = recipesRepository;
        this.mRecipesView = recipesView;
        this.mSchedulerProvider = schedulerProvider;

        mDisposableRecipes = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}