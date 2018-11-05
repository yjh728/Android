package com.example.yjh.mvclogintest;

import android.Manifest;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yjh.mvclogintest.model.UserLoginListener;
import com.example.yjh.mvclogintest.model.UserLoginModel;
import com.example.yjh.mvclogintest.model.UserLoginModelImpl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editAccount;
    private EditText editPassword;
    private AppCompatButton btnLogin;
    private AppCompatButton btnClear;

    private UserLoginModel loginModel;

    private String account = "";
    private String password = "";

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
        loginModel = new UserLoginModelImpl();

        editAccount = findViewById(R.id.edit_account);
        editPassword = findViewById(R.id.edit_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnClear = findViewById(R.id.btn_clear);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                account = editAccount.getText().toString();
                password = editPassword.getText().toString();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "请输入用户名和密码",
                            Toast.LENGTH_SHORT).show();
                } else {
                    loginModel.login(account, password, new UserLoginListener() {
                        @Override
                        public void loginSucceed() {
                            Log.d("登录成功", "登录成功");
                            Toast.makeText(MainActivity.this, "登录成功",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void loginFailed() {
                            Log.d("登录失败", "登录失败");
                            Toast.makeText(MainActivity.this, "登录失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.btn_clear:
                editAccount.setText("");
                editPassword.setText("");
                break;
        }
    }
}
