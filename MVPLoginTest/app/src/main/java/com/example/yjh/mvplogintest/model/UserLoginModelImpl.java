package com.example.yjh.mvplogintest.model;

public class UserLoginModelImpl implements UserLoginModel {
    @Override
    public void login(final String account, final String password, final UserLoginListener loginListener) {
        if (account.equals("admin") && password.equals("123456")) {
            loginListener.loginSucceed();
        } else {
            loginListener.loginFailed();
        }
    }
}
