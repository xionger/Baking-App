package com.xiongxh.baking_app.recipes;

import android.support.annotation.NonNull;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.data.Interactor.RecipesInteractor;
import com.xiongxh.baking_app.data.RecipesRepository;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.local.RecipesLocalDataSource;
import com.xiongxh.baking_app.data.remote.RecipesRemoteDataSource;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;

public class RecipesPresenter implements RecipesContract.Presenter{

    private RecipesContract.View mRecipesView;
    private RecipesRepository mRecipesRepository;

    private RecipesInteractor mRecipesInteractor;

    private CompositeDisposable mDisposableRecipes = new CompositeDisposable();
    /*
    public RecipesPresenter(){
        mRecipesInteractor = new RecipesInteractor();
    }
    */

    public RecipesPresenter(RecipesContract.View view){
        //RecipesLocalDataSource localDataSource = new RecipesLocalDataSource();
        //RecipesRemoteDataSource remoteDataSource = new RecipesRemoteDataSource();

        //mRecipesRepository = new RecipesRepository(localDataSource, remoteDataSource);
        mRecipesInteractor = new RecipesInteractor();

        mRecipesView = view;
    }

    @Override
    public RecipesContract.View getView(){
        return mRecipesView;
    }

    /*
    public RecipesPresenter(@NonNull RecipesRepository recipesRepository,
                            @NonNull RecipesContract.View recipesView,
                            @NonNull BaseSchedulerProvider schedulerProvider
                            ){
        this.mRecipeRepository = checkNotNull(recipesRepository, "RecipeRepository cannot be null");
        this.mRecipesView = checkNotNull(recipesView, "RecipeView cannot be null");
        this.mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");

        mDisposableRecipes = new CompositeDisposable();

        mRecipesView.setPresenter(this);
    }*/

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
        //mDisposableRecipes.clear();

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

    /*
    @Override
    public void loadRecipes(boolean forceUpdate) {
        loadRecipes(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }
    */

    /*
    @Override
    public void loadRecipesFromRepository(final boolean forceUpdate, RecipesIdlingResource resource){

        if (forceUpdate){
            //mRecipeRepository.markRepoAsSynced(false);
        }

        mDisposableRecipes.clear();

        Disposable disposableRecipe = mRecipeRepository
                .getRecipes()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .doOnSubscribe(disposable -> {
                    mRecipesView.showLoadingIndicator(true);
                    if (resource != null) {
                        resource.setIdleState(false);
                    }
                })
                .subscribe(
                        //onNext
                        recipes -> {
                            mRecipesView.showRecipeList(recipes);
                            //mRecipeRepository.markRepoAsSynced(true);
                            mRecipesView.showLoadingIndicator(false);

                            if (resource != null){
                                resource.setIdleState(true);
                            }
                            if (forceUpdate){
                                mRecipesView.showLoadingRecipesCompletedMessage();
                            }

                        },
                        //onError
                        throwable -> {
                            mRecipesView.showLoadingIndicator(false);
                            mRecipesView.showLoadingRecipesErrorMessage();
                            //mRecipeRepository.markRepoAsSynced(false);
                        }
                );

        mDisposableRecipes.add(disposableRecipe);
    }*/



}