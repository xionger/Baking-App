package com.xiongxh.bakingapp.di.component;

import android.content.Context;

import com.xiongxh.bakingapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Retrofit exposeRetrofit();

    Context exposeContext();
}
