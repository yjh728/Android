package com.example.yjh.viewpagertest;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyFragmentPageAdater extends FragmentStatePagerAdapter {

    private FragmentManager mFragmentManager;

    private List<Fragment> mFragmentList;

    public MyFragmentPageAdater(FragmentManager fm) {
        super(fm);
        this.mFragmentManager = fm;
        this.mFragmentList = mFragmentManager.getFragments();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    /**
     * 获取Fragment的总数
     * @return
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 为给定的位置创建相应的View。创建View之后,需要在该方法中自行添加到container中。
     * @param container ViewPager本身
     * @param position  给定的位置
     * @return 提交给ViewPager进行保存的实例对象
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    /**
     * 当ViewPager内容有变化时，进行调用
     * @param container
     */
    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        super.startUpdate(container);
    }

    /**
     * 确认View与实例对象是否相互对应。ViewPager内部用于获取View对性的ItemInfo
     * @param view  ViewPager显示的VIew内容
     * @param object    在instantiateItem中提交给ViewPager进行保存的实例对象
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 为指定的位置移除相应view
     * @param container viewpager本身
     * @param position  给定的位置
     * @param object    在instantiateItem中提交给viewpager进行保存的实例对象
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    /**
     * 保存PagerAdapter关联的任何实例状态
     * @return  PagerAdapter保存状态
     */
    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    /**
     * 恢复与PagerAdapter关联的任何实例状态
     * @param state PagerAdapter保存状态
     * @param loader    用于实例化还原对象的类加载器
     */
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }

    /**
     * ViewPager调用该方法来通知PagerAdapter当前ViewPager显示的主要项，提供给用户对主要项进行操作的方法
     * @param container ViewPager本身
     * @param position  给定的位置
     * @param object    在instantiateItem中提交给ViewPager进行保存的实例对象
     */
    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 多用于TabLayout与ViewPager进行绑定时提供显示的标题
     * @param position  给定位置
     * @return  显示标题
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
