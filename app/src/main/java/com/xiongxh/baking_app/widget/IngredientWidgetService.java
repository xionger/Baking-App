package com.xiongxh.baking_app.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class IngredientWidgetService extends RemoteViewsService {
    public static final String INGREDIENTS_KEY = "INGREDIENTS";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return null;
    }
}
