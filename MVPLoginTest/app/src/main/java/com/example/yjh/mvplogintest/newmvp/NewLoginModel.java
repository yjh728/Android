package com.example.yjh.mvplogintest.newmvp;

import android.os.Handler;
import android.os.SystemClock;

import com.example.yjh.mvplogintest.model.UserLoginListener;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class NewLoginModel implements LoginContract.Model {
    @Override
    public void login(final String account, final String password, final UserLoginListener loginListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (account.equals("admin") && password.equals("123456")) {
                    loginListener.loginSucceed();
                } else {
                    loginListener.loginFailed();
                }
            }
        }, 2000);

    }

    @Override
    public Observable<String> rxLogin(final String account, final String password) {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                SystemClock.sleep(3000);
                if (account.equals("admin") && password.equals("123456")) {
                    emitter.onNext("SUCCESS");
                    emitter.onComplete();
                } else {
                    emitter.onNext("FAILED");
                    emitter.onComplete();
                }
            }
        });
    }
}
