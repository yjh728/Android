package com.example.yjh.mvplogintest.base;

import android.content.Context;

//T代表View，E代表Model
public abstract class BasePresenter<T, E> {
    public Context mContext;

    public T mView;
    public E mModel;

    public void setViewAndModel(T view, E model) {
        this.mView = view;
        this.mModel = model;
    }

    //RxJava中使用的
//    public void onStart(){}
//
//    public void onDestory(){}
}
