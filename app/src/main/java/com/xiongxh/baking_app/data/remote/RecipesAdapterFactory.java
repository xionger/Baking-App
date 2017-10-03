package com.xiongxh.baking_app.data.remote;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class RecipesAdapterFactory implements TypeAdapterFactory{
    public static TypeAdapterFactory create() {
        //return new AutoValueGson_RecipesAdapterFactory();
        return null;
    }
}
