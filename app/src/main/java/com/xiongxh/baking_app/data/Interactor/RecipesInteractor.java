package com.xiongxh.baking_app.data.Interactor;


import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.data.RecipesRepository;
import com.xiongxh.baking_app.data.bean.Recipe;
import com.xiongxh.baking_app.data.local.RecipesLocalDataSource;
import com.xiongxh.baking_app.data.remote.RecipesRemoteDataSource;
import com.xiongxh.baking_app.rx.RxScheduler;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.Observable;
import timber.log.Timber;

public class RecipesInteractor {
    private RecipesRepository mRecipesRepository;

    public RecipesInteractor(){
        this.mRecipesRepository =
                new RecipesRepository(new RecipesLocalDataSource(),
                        new RecipesRemoteDataSource());
    }

    public Single<List<Recipe>> getRecipes(){
        if (mRecipesRepository.isSynced()){
            Timber.d("Data are updated. Retrieve data from local database.");
            return mRecipesRepository.getRecipes().compose(RxScheduler.applySchedulersSingle());
        }

        return mRecipesRepository.getRecipes()
                .flattenAsObservable(recipes -> recipes)
                //.flatMap(recipe -> Observable.just(recipe).subscribeOn(Scheduler.io()))
                .toList()
                .doOnSuccess(recipes -> {
                    Timber.d("Saving recipe list inte the database");
                    mRecipesRepository.setRecipes(recipes);
                    BakingApp.get().recipePreferences.setRecipesSynced(true);
                })
                .compose(RxScheduler.applySchedulersSingle());
    }
}
