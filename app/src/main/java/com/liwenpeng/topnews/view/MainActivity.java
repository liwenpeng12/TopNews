package com.liwenpeng.topnews.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.liwenpeng.topnews.MyApplication;
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.ListSearchHistoryAdapter;
import com.liwenpeng.topnews.adapter.MainViewPagerAdapter;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.bean.table_search_history.SearchHistoryBean;
import com.liwenpeng.topnews.bean.table_top.GDBean;
import com.liwenpeng.topnews.bean.table_top.GDBeanDao;
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


import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity {
    private static final String TAG = "MainActivity";
    private static final int TAKE_PHOTO = 1;
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
    private ListView listView;
    private List<GDBean> list;
    private ListSearchHistoryAdapter listSearchHistoryAdapter;
    private NavigationView navigationView;
    //  private RecyclerView recyclerView;
    //circleimage拍照
    private Uri imageUri;
    private de.hdodenhof.circleimageview.CircleImageView imageViewheader;
    private View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "mTabName.length :" + mTabName.length);

        initView();
        initData();
    }

    private void initData() {
      //点击了焦点后的变化
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(MainActivity.this,"onFocusChange",Toast.LENGTH_SHORT).show();

            }
        });

        //进行了搜索点击或者输入字体后调用的方法
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(MainActivity.this,"onQueryTextSubmit :"+s,Toast.LENGTH_SHORT).show();
                //模糊搜索判断是否有该字段，如果没有则提示没有数据不保存到数据库，如果有则将字符串和对应的url保存
                QueryBuilder<GDBean> qb = MyApplication.getInstances().getDaoSession().getGDBeanDao().queryBuilder();
                qb.where(GDBeanDao.Properties.Title.like("%"+s+"%"));
                List<GDBean> list = qb.list();


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
            if (!TextUtils.isEmpty(s)){
                Toast.makeText(MainActivity.this,"onQueryTextChange不为空",Toast.LENGTH_SHORT).show();
                //进行数据库字段模糊搜索
                QueryBuilder<GDBean> qb = MyApplication.getInstances().getDaoSession().getGDBeanDao().queryBuilder();
                qb.where(GDBeanDao.Properties.Title.like("%"+s+"%"));
                list = qb.list();
                listSearchHistoryAdapter = new ListSearchHistoryAdapter(MainActivity.this, list);
                listView.setAdapter(listSearchHistoryAdapter);
                return true;
            }else {
                Toast.makeText(MainActivity.this,"onQueryTextChange为空",Toast.LENGTH_SHORT).show();
                list.clear();
                listSearchHistoryAdapter.notifyDataSetChanged();

                return false;
            }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,UrlDetailActivity.class);
                intent.putExtra("INTENT_URL",list.get(i).getUrl());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tabLayout = findViewById(R.id.main_tab_layout);
        viewPager = findViewById(R.id.main_iew_pager);
        searchView = findViewById(R.id.main_searchView);
        listView = findViewById(R.id.main_listview);
        navigationView = findViewById(R.id.navgation_view);
        headerView = navigationView.getHeaderView(0);
        imageViewheader = headerView.findViewById(R.id.iv_header);
        imageViewheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击更换图片",Toast.LENGTH_SHORT).show();
                File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                if (outputImage.exists()){
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >=24){
                    imageUri = FileProvider.getUriForFile(MainActivity.this,"com.liwenpeng.datashare",outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent =new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);


            }
        });
        //  recyclerView = findViewById(R.id.search_recycleview);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageViewheader.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
