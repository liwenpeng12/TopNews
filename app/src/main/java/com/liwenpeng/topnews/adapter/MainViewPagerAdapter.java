package com.liwenpeng.topnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * liwenpeng
 * 2018/4/1 22:25
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragment;
    private int Count;

    public MainViewPagerAdapter(FragmentManager fm, Fragment[] fragments, int count) {
        super(fm);
        this.fragment = fragments;
        Count = count;
    }

    @Override
    public Fragment getItem(int position) {

        return fragment[position];
    }

    @Override
    public int getCount() {
        return fragment.length;
    }
}
