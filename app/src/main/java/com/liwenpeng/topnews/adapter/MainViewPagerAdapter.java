package com.liwenpeng.topnews.adapter;

import android.support.annotation.Nullable;
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
    private String[] tabname;
    public MainViewPagerAdapter(FragmentManager fm, Fragment[] fragments, int count, String[] mTabName) {
        super(fm);
        this.fragment = fragments;
        Count = count;
        this.tabname = mTabName;
    }

    @Override
    public Fragment getItem(int position) {

        return fragment[position];
    }

    @Override
    public int getCount() {
        return fragment.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       return tabname[position];

    }
}
