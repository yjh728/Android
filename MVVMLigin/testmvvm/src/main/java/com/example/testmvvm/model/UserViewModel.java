package com.example.testmvvm.model;

import android.content.Context;
import android.widget.Toast;

public class UserViewModel {

    private Context mContext;

    private UserModel userModel;

    public UserViewModel(Context context, UserModel userModel) {
        this.mContext = context;
        this.userModel = userModel;
    }

    public void showName() {
        Toast.makeText(mContext, userModel.getName(), Toast.LENGTH_SHORT).show();
    }
}
