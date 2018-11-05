package com.example.yjh.mvplogintest.newmvp;

import android.app.ProgressDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yjh.mvplogintest.R;
import com.example.yjh.mvplogintest.base.BaseActivity;

public class NewMVPLoginActivity extends BaseActivity<NewLoginPresenter, NewLoginModel> implements View.OnClickListener, LoginContract.View {

    private EditText editAccount;

    private EditText editPassword;

    private AppCompatButton btnLogin;

    private AppCompatButton btnClear;

    private ProgressDialog dialog;

    @Override
    protected void initPresenter() {
        mPresenter.setViewAndModel(this, mModel);
    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在登录,请稍后...");
        dialog.setCancelable(false);
        editPassword = findViewById(R.id.edit_pwd);
        editAccount = findViewById(R.id.edit_account);
        btnLogin = findViewById(R.id.btn_login);
        btnClear = findViewById(R.id.btn_clear);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void success() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clear() {
        editAccount.setText("");
        btnClear.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
//                mPresenter.login(editAccount.getText().toString(), editPassword.getText().toString());
                mPresenter.rxLogin(editAccount.getText().toString(), editPassword.getText().toString());
                break;
            case R.id.btn_clear:
                mPresenter.clear();
                break;
        }
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void closeLoading() {
        dialog.dismiss();
    }
}
