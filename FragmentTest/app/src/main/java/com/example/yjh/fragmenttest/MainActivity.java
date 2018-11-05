package com.example.yjh.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }

    private void replaceFragment(Fragment fragment) {
        /*//在活动中通过调用方法获得FragmentManager实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启一个事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //向容器内添加或替换碎片，需要传入容器的id和待添加的碎片实例
        transaction.replace(R.id.right_layout, fragment);
        //用于将一个事物添加到返回栈中,参数一般用于描述返回栈的状态，一般传入null即可
        transaction.addToBackStack(null);
        transaction.commit();*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                replaceFragment(new AnotherRightFragment());
                break;
            default:
                break;
        }
    }
}
