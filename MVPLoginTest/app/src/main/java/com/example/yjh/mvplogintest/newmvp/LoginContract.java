package com.example.yjh.mvplogintest.newmvp;

import io.reactivex.Observable;

import com.example.yjh.mvplogintest.base.BaseModel;
import com.example.yjh.mvplogintest.base.BasePresenter;
import com.example.yjh.mvplogintest.base.BaseView;
import com.example.yjh.mvplogintest.model.UserLoginListener;

public class LoginContract {
    interface Model extends BaseModel{
        void login(String account, String password, UserLoginListener loginListener);
        Observable<String> rxLogin(String account, String password);
    }
    interface View extends BaseView{
        void success();
        void failed();
        void clear();
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        abstract void login(String account, String password);
        abstract void rxLogin(String account, String password);
        abstract void clear();
    }
}
