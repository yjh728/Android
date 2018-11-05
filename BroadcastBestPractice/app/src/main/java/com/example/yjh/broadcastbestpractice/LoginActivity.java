package com.example.yjh.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private EditText account;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = (EditText) findViewById(R.id.account_text);
        password = (EditText) findViewById(R.id.password_text);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account1 = account.getText().toString();
                String password1 = password.getText().toString();
                if (account1.equals("admin") && password1.equals("admin")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
