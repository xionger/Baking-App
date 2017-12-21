package com.xiongxh.baking_app.recipedetail;


import android.support.annotation.NonNull;

import com.xiongxh.baking_app.data.Interactor.RecipeInteractor;
import com.xiongxh.baking_app.data.bean.Recipe;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

public class RecipeDetailPresenter implements RecipeDetailContract.Presenter{

    private RecipeDetailContract.View mRecipeView;
    private RecipeInteractor mRecipeInteractor;
    private CompositeDisposable mDisposableRecipe = new CompositeDisposable();
    private int mRecipeId;

    public RecipeDetailPresenter(RecipeDetailContract.View view, int recipeId){
        this.mRecipeView = view;
        this.mRecipeId = recipeId;
        mRecipeInteractor = new RecipeInteractor();
    }

    @Override
    public RecipeDetailContract.View getView() {
        return mRecipeView;
    }

    @Override
    public void subscribe(RecipeDetailContract.View view) {
        this.mRecipeView = view;
        loadRecipeDetails();
    }

    @Override
    public void unsubscribe() {
        mDisposableRecipe.clear();
    }

    @Override
    public void loadRecipeDetails() {
        Timber.d("Loading recipe ...");
        //mRecipeView.showLoadingIndicator(true);

        Disposable disposableRecipe = mRecipeInteractor
                .getRecipeDetail(mRecipeId)
                .subscribeWith(new DisposableSingleObserver<Recipe>(){
                    @Override
                    public void onSuccess(@NonNull Recipe recipe){
                        mRecipeView.showRecipeDetails(recipe);
                        mRecipeView.showIngredients(recipe.getIngredients());
                        mRecipeView.showSteps(recipe.getSteps());
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        Timber.e(e);
                        mRecipeView.showMessage(e.getMessage());
                    }
                });

        mDisposableRecipe.add(disposableRecipe);
    }
}
