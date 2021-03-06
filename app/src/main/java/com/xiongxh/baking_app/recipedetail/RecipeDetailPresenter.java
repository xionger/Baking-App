package com.xiongxh.baking_app.recipedetail;

import com.xiongxh.baking_app.data.Interactor.RecipeInteractor;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

public class RecipeDetailPresenter implements RecipeDetailContract.Presenter{

    private RecipeDetailContract.View mRecipeView;
    private RecipeInteractor mRecipeInteractor;
    private CompositeDisposable mDisposableRecipe = new CompositeDisposable();
    private int mRecipeId;

    public RecipeDetailPresenter(){
        mRecipeInteractor = new RecipeInteractor();
    }

    @Override
    public RecipeDetailContract.View getView() {
        return mRecipeView;
    }

    @Override
    public void subscribe(RecipeDetailContract.View view) {
        Timber.d("Entering subscribe ...");
        this.mRecipeView = view;
        loadRecipeDetails();
    }

    @Override
    public void unsubscribe() {
        mDisposableRecipe.clear();
    }

    @Override
    public void loadRecipeDetails() {
        Timber.d("Loading recipe detail...");

        Disposable disposableRecipe = mRecipeInteractor
                .getRecipeDetail(mRecipeId)
                .subscribeWith(new DisposableSingleObserver<Recipe>(){
                    @Override
                    public void onSuccess(@NonNull Recipe recipe){
                        mRecipeView.showRecipeDetails(recipe);
                        mRecipeView.showIngredients(recipe.getIngredients());
                        mRecipeView.showSteps(recipe.getSteps());
                        mRecipeView.showRecipeName(recipe.getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        Timber.e(e);
                        mRecipeView.showErrorMessage(e.getMessage());
                    }
                });

        mDisposableRecipe.add(disposableRecipe);
    }

    @Override
    public void fetchStepData(int stepId) {
        Timber.d("Loading step data ..., StepId: " + stepId);
        Disposable disposableStep = mRecipeInteractor
                .getRecipeDetail(mRecipeId)
                .subscribeWith(new DisposableSingleObserver<Recipe>(){
                    @Override
                    public void onSuccess(@NonNull Recipe recipe){
                        Step step = recipe.getSteps().get(stepId);
                        mRecipeView.refreshStepContainer(
                                step.getDescription(),
                                step.getVideoURL(),
                                step.getThumbnailURL());
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        Timber.e(e);
                        mRecipeView.showErrorMessage(e.getMessage());
                    }
                });

        mDisposableRecipe.add(disposableStep);
    }

    @Override
    public void openStepDetails(int stepId) {
        mRecipeView.showStepDetails(stepId);
    }

    @Override
    public void setRecipeId(int recipeId){
        this.mRecipeId = recipeId;
    }

}
