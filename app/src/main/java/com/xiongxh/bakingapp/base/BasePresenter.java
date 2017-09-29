package com.xiongxh.bakingapp.base;


import android.view.View;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public interface BasePresenter {
    void subscribe();
    void unsubscribe();

}

