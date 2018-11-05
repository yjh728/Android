package com.example.yjh.mvplogintest.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.yjh.mvplogintest.LoginView;
import com.example.yjh.mvplogintest.model.UserLoginListener;
import com.example.yjh.mvplogintest.model.UserLoginModel;
import com.example.yjh.mvplogintest.model.UserLoginModelImpl;

public class LoginPresenter {

    private LoginView mLoginView;

    private UserLoginModel userLoginModel;

    private Context mContext;

    public LoginPresenter(LoginView mLoginView, Context mContext) {
        this.mLoginView = mLoginView;
        this.mContext = mContext;
        userLoginModel = new UserLoginModelImpl();
    }

    public void login(String account, String password) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        } else {
            userLoginModel.login(account, password, new UserLoginListener() {
                @Override
                public void loginSucceed() {
                    mLoginView.succeed();
                }

                @Override
                public void loginFailed() {
                    mLoginView.failed();
                }
            });
        }
    }

    public void clear(){
        mLoginView.clear();
    }
}
