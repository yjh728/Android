package com.example.yjh.viewpagertest;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {

    private List<View> mViewList = new ArrayList<>();

    public MyViewPagerAdapter() {
    }

    public MyViewPagerAdapter(List<View> viewList) {
        super();
        mViewList = viewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

}
