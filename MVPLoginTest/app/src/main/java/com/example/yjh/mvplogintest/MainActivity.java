package com.example.yjh.mvplogintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yjh.mvplogintest.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginView{

    private EditText editAccount;

    private EditText editPassword;

    private AppCompatButton btnLogin;

    private AppCompatButton btnClear;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    private void initView() {
        presenter = new LoginPresenter(this, this);
        editPassword = findViewById(R.id.edit_pwd);
        editAccount  =findViewById(R.id.edit_account);
        btnLogin = findViewById(R.id.btn_login);
        btnClear = findViewById(R.id.btn_clear);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                presenter.login(editAccount.getText().toString(), editPassword.getText().toString());
                break;
            case R.id.btn_clear:
                presenter.clear();
                break;
        }
    }

    @Override
    public void succeed() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clear() {
        editAccount.setText("");
        editPassword.setText("");
    }
}
