package com.example.yjh.mvclogintest.model;


import android.os.Handler;
import android.util.Log;

public class UserLoginModelImpl implements UserLoginModel {
    @Override
    public void login(final String account, final String password, final UserLoginListener loginListener) {
        Log.d("登录中", "登录中");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (account.equals("admin") && password.equals("123456")) {
                    loginListener.loginSucceed();
                } else {
                    loginListener.loginFailed();
                }
            }
        }, 3000);
    }
}
