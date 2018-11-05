package com.example.yjh.viewpagertest;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private List<View> mViewList;

    private MyViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mViewPagerAdapter = new MyViewPagerAdapter();
        mViewList = new ArrayList<>();
    }
}
