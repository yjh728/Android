package com.example.yjh.mvclogintest.model;

public interface UserLoginModel {
    public abstract void login(String account, String password, UserLoginListener loginListener);
}
