package com.xiongxh.baking_app.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.xiongxh.baking_app.R;
import com.xiongxh.baking_app.data.bean.Ingredient;
import com.xiongxh.baking_app.data.bean.Recipe;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class IngredientWidgetService extends RemoteViewsService {
    public static final String INGREDIENTS_KEY = "INGREDIENTS";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Timber.d("Entering RemoteViewsFactory ...");
        return new RemoteViewFactory(getApplicationContext(), intent);
    }

    class RemoteViewFactory implements RemoteViewsService.RemoteViewsFactory{
        private final Context context;
        private final Intent intent;
        private List<Ingredient> mIngredients = new ArrayList<>();

        public RemoteViewFactory(Context context, Intent intent){
            this.context = context;
            this.intent = intent;
        }

        @Override
        public void onCreate() {
            Recipe recipe =
                    new Gson().fromJson(intent.getStringExtra(INGREDIENTS_KEY), Recipe.class);
            mIngredients = recipe.getIngredients();
        }

        @Override
        public RemoteViews getViewAt(int pos) {
            Ingredient ingredient = mIngredients.get(pos);

            RemoteViews remoteViews =
                    new RemoteViews(context.getPackageName(), R.layout.item_ingredient_widget);

            remoteViews.setTextViewText(R.id.measure, ingredient.getMeasure());
            remoteViews.setTextViewText(R.id.quantity, String.valueOf(ingredient.getQuantity()));
            remoteViews.setTextViewText(R.id.ingredient_item_name, ingredient.getIngredient());

            return remoteViews;
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return mIngredients.size();
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
