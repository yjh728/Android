package com.example.yjh.mvplogintest.model;

public interface UserLoginModel {
    void login(String account, String password, UserLoginListener loginListener);
}
