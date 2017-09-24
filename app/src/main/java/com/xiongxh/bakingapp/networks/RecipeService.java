package com.xiongxh.bakingapp.networks;


import com.xiongxh.bakingapp.models.Recipe;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RecipeService {

    private RecipeAPI recipeAPI;

    public RecipeService(){}

    public RecipeService(RecipeAPI recipeAPI){
        this.recipeAPI = recipeAPI;
    }

    public Subscription getRecipeList(final GetRecipeListCallback callback){
        return recipeAPI.getRecipeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, Observable<? extends Recipe>>() {
                    @Override
                    public Observable<? extends Recipe> apply(@NonNull Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                }).subscribe(new Subscriber<Recipe>(){
                    @Override
                    public void onComplete(){}

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(Recipe recipe){
                        callback.onSuccess(recipe);
                    }
                });
    }

    public interface GetRecipeListCallback{
        void onSuccess(Recipe recipe);

        void onError(NetworkError networkError);
    }
}
