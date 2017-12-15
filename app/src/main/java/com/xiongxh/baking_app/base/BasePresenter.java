package com.xiongxh.baking_app.base;

public interface BasePresenter<V> {
    V getView();

    void subscribe(V view);
    void unsubscribe();
    //void start();

}