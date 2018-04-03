package com.liwenpeng.topnews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * liwenpeng
 * 2018/4/1 22:25
 * 问题:tab重复出现
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private final  String TAg = "MainViewPagerAdapter";

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

    //既然在此处加了tab的标题，就不用在activity中进行addTab的操作
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAg,"tabname[position] :"+tabname[position]);
       return tabname[position];

    }
}
