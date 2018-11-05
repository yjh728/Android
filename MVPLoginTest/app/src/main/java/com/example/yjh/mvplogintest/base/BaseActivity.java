package com.example.yjh.mvplogintest.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yjh.mvplogintest.util.MVPUtil;

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {

    protected T mPresenter;
    protected E mModel;
    protected Context activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        activity = this;
        mPresenter = MVPUtil.getT(this, 0);
        mModel = MVPUtil.getT(this, 1);
        if (null!= mPresenter) {
            mPresenter.mContext = this;
        }
        initView();
        initListener();
        initPresenter();
    }

    protected abstract void initPresenter();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract int initLayout();
}
