package com.liwenpeng.topnews.view;

import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.MainViewPagerAdapter;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.view.fragment.CaiJingFragment;
import com.liwenpeng.topnews.view.fragment.GuoJiFragment;
import com.liwenpeng.topnews.view.fragment.GuoNeiFragment;
import com.liwenpeng.topnews.view.fragment.JunShiFragment;
import com.liwenpeng.topnews.view.fragment.KeJiFragment;
import com.liwenpeng.topnews.view.fragment.ShehuiFragment;
import com.liwenpeng.topnews.view.fragment.ShiShangFragment;
import com.liwenpeng.topnews.view.fragment.TiYuFragment;
import com.liwenpeng.topnews.view.fragment.TopFragment;
import com.liwenpeng.topnews.view.fragment.YuLeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<NewsBean> mNewsBeanList = new ArrayList<>();
    private NewsBean topResponse;
    private NewsBean shehuiResponse;
    private NewsBean guoneiResponse;
    private NewsBean guojiResponse;
    private NewsBean yuleResponse;
    private NewsBean tiyuResponse;
    private NewsBean junshiResponse;
    private NewsBean kejiResponse;
    private NewsBean caijingResponse;
    private NewsBean shishangResponse;
    private String[] mTabName = new String[]{"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private TopFragment topFragment;
    private ShehuiFragment shehuiFragment;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private boolean hasClickedBack = false;
    private Fragment[] fragments = new Fragment[]{new TopFragment(), new ShehuiFragment(),new GuoNeiFragment(),
    new GuoJiFragment(),new YuLeFragment(),new TiYuFragment(),
    new JunShiFragment(),new KeJiFragment(),new CaiJingFragment(),new ShiShangFragment()};
    private SearchView searchView;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "mTabName.length :" + mTabName.length);
        initView();
    }
    private void initView() {
        tabLayout = findViewById(R.id.main_tab_layout);
        viewPager = findViewById(R.id.main_iew_pager);
        searchView = findViewById(R.id.main_searchView);
        recyclerView = findViewById(R.id.search_recycleview);
        Log.d(TAG, "fragment :" + fragments);
        if (mainViewPagerAdapter == null) {
            mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments, mTabName.length, mTabName);
        }

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
