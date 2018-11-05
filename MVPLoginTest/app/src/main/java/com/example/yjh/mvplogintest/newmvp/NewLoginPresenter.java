package com.example.yjh.mvplogintest.newmvp;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.yjh.mvplogintest.model.UserLoginListener;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewLoginPresenter extends LoginContract.Presenter {
    @Override
    void login(String account, String password) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "请输入账号和密码", Toast.LENGTH_SHORT).show();
        } else {
            mView.showLoading();
            mModel.login(account, password, new UserLoginListener() {
                @Override
                public void loginSucceed() {
                    mView.success();
                    mView.closeLoading();
                }

                @Override
                public void loginFailed() {
                    mView.failed();
                    mView.closeLoading();
                }
            });
        }
    }

    @Override
    void rxLogin(String account, String password) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "请输入账号和密码", Toast.LENGTH_SHORT).show();
        } else {
            mView.showLoading();
            mModel.rxLogin(account, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            //mView.closeLoading();
                        }

                        @Override
                        public void onNext(String s) {
                            mView.closeLoading();
                            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.closeLoading();
                        }

                        @Override
                        public void onComplete() {
                            mView.closeLoading();
                        }
                    });
        }
    }


    @Override
    void clear() {
        mView.clear();
    }
}
