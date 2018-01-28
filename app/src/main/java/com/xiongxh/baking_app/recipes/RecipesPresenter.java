package com.xiongxh.baking_app.recipes;

import android.support.annotation.NonNull;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.data.Interactor.RecipesInteractor;
import com.xiongxh.baking_app.data.RecipesRepository;
import com.xiongxh.baking_app.data.bean.Recipe;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

public class RecipesPresenter implements RecipesContract.Presenter{

    private RecipesContract.View mRecipesView;
    private RecipesRepository mRecipesRepository;

    private RecipesInteractor mRecipesInteractor;

    private CompositeDisposable mDisposableRecipes = new CompositeDisposable();

    public RecipesPresenter(){
        mRecipesInteractor = new RecipesInteractor();
    }

    @Override
    public RecipesContract.View getView(){
        return mRecipesView;
    }

    @Override
    public void subscribe(RecipesContract.View view) {
        this.mRecipesView = view;
        loadRecipes();
    }

    @Override
    public void unsubscribe() {
        mDisposableRecipes.clear();

    }

    @Override
    public void loadRecipes(){
        Timber.d("Loading recipes ...");
        mRecipesView.showLoadingIndicator(true);

        Disposable disposableRecipes = mRecipesInteractor
                .getRecipes()
                .subscribeWith(new DisposableSingleObserver<List<Recipe>>() {
                    @Override
                    public void onSuccess(@NonNull List<Recipe> recipes) {
                        mRecipesView.showLoadingIndicator(false);
                        mRecipesView.showRecipeList(recipes);
                        mRecipesView.showLoadingRecipesCompletedMessage();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                        mRecipesView.showLoadingIndicator(false);
                        mRecipesView.showLoadingRecipesErrorMessage(e.getMessage());
                    }
                });

        mDisposableRecipes.add(disposableRecipes);
    }

    @Override
    public void syncData(){
        Timber.d("Set syncData to FALSE.");
        BakingApp.get().recipePreferences.setRecipesSynced(false);
        loadRecipes();
    }

    @Override
    public void openRecipeDetails(int recipeId) {
        mRecipesView.showRecipeDetails(recipeId);
    }

}