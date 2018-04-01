package com.liwenpeng.topnews.view;

import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.MainViewPagerAdapter;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.constant.UrlBaseConstant;
import com.liwenpeng.topnews.util.OkHttpUtil;
import com.liwenpeng.topnews.view.fragment.ShehuiFragment;
import com.liwenpeng.topnews.view.fragment.TopFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    private String[] mTabName= {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private TopFragment topFragment;
    private ShehuiFragment shehuiFragment;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private boolean hasClickedBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

//        requestNet();
    }

    private void initData() {
        //top的数据源
        new Thread(new Runnable() {



            @Override
            public void run() {
                topResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.TOP_URL, Toast.LENGTH_SHORT);
                shehuiResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.SHEHUI_URL, Toast.LENGTH_SHORT);
                guoneiResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.GUONEI_URL, Toast.LENGTH_SHORT);
                guojiResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.GUOJI_URL, Toast.LENGTH_SHORT);
                yuleResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.YULE_URL, Toast.LENGTH_SHORT);
                tiyuResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.TIYU_URL, Toast.LENGTH_SHORT);
                junshiResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.JUNSHI_URL, Toast.LENGTH_SHORT);
                kejiResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.KEJI_URL, Toast.LENGTH_SHORT);
                caijingResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.CAIJING_URL, Toast.LENGTH_SHORT);
                shishangResponse = OkHttpUtil.getOkHttpResponse(MainActivity.this, UrlBaseConstant.SHISHANG_URL, Toast.LENGTH_SHORT);
                mNewsBeanList.add(topResponse);
                mNewsBeanList.add(shehuiResponse);
                mNewsBeanList.add(guoneiResponse);
                mNewsBeanList.add(guojiResponse);
                mNewsBeanList.add(yuleResponse);
                mNewsBeanList.add(tiyuResponse);
                mNewsBeanList.add(junshiResponse);
                mNewsBeanList.add(kejiResponse);
                mNewsBeanList.add(caijingResponse);
                mNewsBeanList.add(shishangResponse);


//                tabLayout.addTab(new TabLayout.Tab().setText(category),0);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String top_category = topResponse.getResult().getData().get(0).getCategory();
//                        String shehui_category = shehuiResponse.getResult().getData().get(0).getCategory();
//                        Log.d(TAG,"shehui_category :"+shehui_category);
//                        String guonei_category = guoneiResponse.getResult().getData().get(0).getCategory();
//                        String guoji_category = guojiResponse.getResult().getData().get(0).getCategory();
//                        String yule_category = yuleResponse.getResult().getData().get(0).getCategory();
//                        String tiyu_category = tiyuResponse.getResult().getData().get(0).getCategory();
//                        String junshi_category = junshiResponse.getResult().getData().get(0).getCategory();
//                        String keji_category = kejiResponse.getResult().getData().get(0).getCategory();
//                        String caijing_category = caijingResponse.getResult().getData().get(0).getCategory();
//                        String shishang_category = shishangResponse.getResult().getData().get(0).getCategory();
//                        TabLayout.Tab top_tab = tabLayout.newTab().setText(mTabName[0]);
//                        tabLayout.addTab(top_tab);
//                        TabLayout.Tab shehuitab = tabLayout.newTab().setText(mTabName[1]);
//                        tabLayout.addTab(shehuitab);
//                        TabLayout.Tab guonei_tab = tabLayout.newTab().setText(mTabName[2]);
//                        tabLayout.addTab(guonei_tab);
//                        TabLayout.Tab guoji_tab = tabLayout.newTab().setText(mTabName[3]);
//                        tabLayout.addTab(guoji_tab);
//                        TabLayout.Tab yule_tab = tabLayout.newTab().setText(mTabName[4]);
//                        tabLayout.addTab(yule_tab);
//                        TabLayout.Tab tiyu_tab = tabLayout.newTab().setText(mTabName[5]);
//                        tabLayout.addTab(tiyu_tab);
//                        TabLayout.Tab junshi_tab = tabLayout.newTab().setText(mTabName[6]);
//                        tabLayout.addTab(junshi_tab);
//                        TabLayout.Tab keji_tab = tabLayout.newTab().setText(mTabName[7]);
//                        tabLayout.addTab(keji_tab);
//                        TabLayout.Tab caijing_tab = tabLayout.newTab().setText(mTabName[8]);
//                        tabLayout.addTab(caijing_tab);
//                        TabLayout.Tab shishangab = tabLayout.newTab().setText(mTabName[9]);
//                        tabLayout.addTab(shishangab);
//                    }
//                });
                Log.d(TAG, "topResponse :" + topResponse);

            }
        }).start();



                        TabLayout.Tab top_tab = tabLayout.newTab().setText(mTabName[0]);
                        tabLayout.addTab(top_tab);
                        TabLayout.Tab shehuitab = tabLayout.newTab().setText(mTabName[1]);
                        tabLayout.addTab(shehuitab);
                        TabLayout.Tab guonei_tab = tabLayout.newTab().setText(mTabName[2]);
                        tabLayout.addTab(guonei_tab);
                        TabLayout.Tab guoji_tab = tabLayout.newTab().setText(mTabName[3]);
                        tabLayout.addTab(guoji_tab);
                        TabLayout.Tab yule_tab = tabLayout.newTab().setText(mTabName[4]);
                        tabLayout.addTab(yule_tab);
                        TabLayout.Tab tiyu_tab = tabLayout.newTab().setText(mTabName[5]);
                        tabLayout.addTab(tiyu_tab);
                        TabLayout.Tab junshi_tab = tabLayout.newTab().setText(mTabName[6]);
                        tabLayout.addTab(junshi_tab);
                        TabLayout.Tab keji_tab = tabLayout.newTab().setText(mTabName[7]);
                        tabLayout.addTab(keji_tab);
                        TabLayout.Tab caijing_tab = tabLayout.newTab().setText(mTabName[8]);
                        tabLayout.addTab(caijing_tab);
                        TabLayout.Tab shishangab = tabLayout.newTab().setText(mTabName[9]);
                        tabLayout.addTab(shishangab);

    }

//    private void requestNet() {
//        OkHttpClient client = new OkHttpClient();
//        Request request =new Request.Builder().url(UrlBaseConstant.TOP_URL).build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Toast.makeText(MainActivity.this,"OKhttp请求数据失败",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //在子线程
//                String body = response.body().string();
//                //Gson解析json数据
//                Gson gson = new Gson();
//                NewsBean newsBean = gson.fromJson(body, NewsBean.class);
//
//            }
//        });
//    }

    private void initView() {
        tabLayout = findViewById(R.id.main_tab_layout);

        viewPager = findViewById(R.id.main_iew_pager);

        initFragment();

        if (mainViewPagerAdapter == null){
            mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList, 10);
        }
        viewPager.setAdapter(mainViewPagerAdapter);



    }

    private void initFragment() {
        if (topFragment == null) {
            topFragment = new TopFragment();
            fragmentList.add(topFragment);
        }
        if (shehuiFragment == null) {
            shehuiFragment = new ShehuiFragment();
            fragmentList.add(shehuiFragment);
        }
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
