package com.xiongxh.baking_app.recipesteps;

import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.util.Util;
import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.data.Interactor.RecipeInteractor;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.bean.Step;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

public class RecipeStepsPresenter implements RecipeStepsContract.Presenter {

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private RecipeStepsContract.View mStepView;
    private int mRecipeId;
    private Recipe mRecipe;

    private RecipeInteractor mStepInteractor;
    private int mCurrentStep = -1;

    public RecipeStepsPresenter(){
        mStepInteractor = new RecipeInteractor();
        //this.mStepView = view;
        //this.mRecipeId = recipeId;
        //this.mCurrentStep = currentStep;
    }

    @Override
    public RecipeStepsContract.View getView(){
        return mStepView;
    }

    @Override
    public void subscribe(RecipeStepsContract.View view){
        this.mStepView = view;
        //loadRecipeDetails();
        loadSteps();
    }

    @Override
    public void unsubscribe(){
        mCompositeDisposable.clear();
    }

    @Override
    public void loadSteps() {
        Timber.d("Loading recipe steps ...");

        Disposable disposableStep = mStepInteractor
                .getRecipeDetail(mRecipeId)
                .subscribeWith(new DisposableSingleObserver<Recipe>(){
                    @Override
                    public void onSuccess(@NonNull Recipe recipe){
                        mRecipe = recipe;
                        Timber.d("step size: " + mRecipe.getSteps().size());
                        mStepView.showStepsInPager(mRecipe.getSteps());
                        mStepView.moveToCurrentStepPage();
                        mStepView.showRecipeName(recipe.getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        Timber.e(e);
                        mStepView.showErrorMessage(e.getMessage());
                    }
                });

        mCompositeDisposable.add(disposableStep);
    }

    @Override
    public void setRecipeId(int recipeId) {
        this.mRecipeId = recipeId;
    }

    /*
    @Override
    public int getCurrentIndex(){
        return mCurrentStep;
    }

    @Override
    public void loadRecipeDetails(){
        Timber.d("Loading recipe details ...");
        Disposable disposableStep = mStepInteractor
                .getRecipeDetail(mRecipeId)
                .subscribeWith(new DisposableSingleObserver<Recipe>(){
                    @Override
                    public void onSuccess(@NonNull Recipe recipe){
                        mRecipe = recipe;
                        showStepAt(mCurrentStep);
                        mStepView.showRecipeName(recipe.getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        Timber.e(e);
                        mStepView.showErrorMessage(e.getMessage());
                    }
                });

        mCompositeDisposable.add(disposableStep);
    }

    @Override
    public void showStep(Step step){
        mCurrentStep = step.getIdx();
        showStepAt(mCurrentStep);
    }

    @Override
    public void showNextStep(){
        mCurrentStep++;
        showStepAt(mCurrentStep);
    }

    @Override
    public void showPreviousStep(){
        mCurrentStep--;
        showStepAt(mCurrentStep);
    }

    private void showStepAt(int position){

        mStepView.setBackButton(position != 0);
        mStepView.setNextButton(position != mRecipe.getSteps().size() - 1);
        mStepView.setStepNumber(mCurrentStep + "/" + (mRecipe.getSteps().size() -1));

        Step step = mRecipe.getSteps().get(position);

        Timber.d("position: " + position + "; step description: " + step.getDescription());

        SimpleExoPlayer simpleExoPlayer = null;

        if (null != step.getVideoURL() && !step.getVideoURL().isEmpty()){
            simpleExoPlayer = ExoPlayerFactory
                    .newSimpleInstance(BakingApp.get(), new DefaultTrackSelector());

            Uri uri = Uri.parse(step.getVideoURL());

            Timber.d("uri: " + uri);

            String userAgent = Util.getUserAgent(BakingApp.get(), "Recipe Step");

            Timber.d("useragent: " + userAgent);

            OkHttpDataSource.Factory factory =
                    new OkHttpDataSourceFactory(BakingApp.get().client, userAgent, null);

            MediaSource mediaSource =
                    new ExtractorMediaSource(uri, factory, new DefaultExtractorsFactory(), null, null);

            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }
        if (simpleExoPlayer == null){
            Timber.d("simpleexoplayer is null...");
        }else{
            Timber.d("simpleexoplayer is not null...");
        }

        mStepView.showStep(step, simpleExoPlayer);
    }
    */
}
