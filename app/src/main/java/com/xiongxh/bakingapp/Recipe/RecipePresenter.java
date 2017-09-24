package com.xiongxh.bakingapp.Recipe;

import com.xiongxh.bakingapp.networks.RecipeService;
import org.reactivestreams.Subscription.CompositeSubscription;


public class RecipePresenter {

    private final RecipeService mRecipeService;
    private final BaseView mBaseView;
    private CompositeSubscription subscriptions;
}
