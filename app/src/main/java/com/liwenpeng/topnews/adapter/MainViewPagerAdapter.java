package com.liwenpeng.topnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * liwenpeng
 * 2018/4/1 22:25
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private int Count;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, int count) {
        super(fm);
        this.fragmentList = fragmentList;
        Count = count;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return Count;
    }
}
