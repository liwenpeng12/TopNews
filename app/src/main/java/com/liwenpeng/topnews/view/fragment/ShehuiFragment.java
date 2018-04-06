package com.liwenpeng.topnews.view.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.liwenpeng.topnews.MyApplication;
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.RecycleViewAdapter;
import com.liwenpeng.topnews.bean.table_shehui.SocialBean;
import com.liwenpeng.topnews.bean.table_shehui.SocialBeanDao;

import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.bean.table_top.GDBean;
import com.liwenpeng.topnews.constant.UrlBaseConstant;
import com.liwenpeng.topnews.util.NetStatusUtil;
import com.liwenpeng.topnews.util.OkHttpUtil;
import com.liwenpeng.topnews.util.ToolUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * liwenpeng
 * 2018/4/1 22:28
 */
public class ShehuiFragment extends LazyLoadFragment  {
    private String url = UrlBaseConstant.SHEHUI_URL;

    private RecyclerView recyclerView;
    private NewsBean topResponse;
    private static final String TAg = "TopFragment";
    private List<NewsBean.ResultBean.DataBean> data;
    private List<NewsBean.ResultBean.DataBean> mCutData =new ArrayList<NewsBean.ResultBean.DataBean>();



    private RecycleViewAdapter adapter;
    private RefreshLayout refreshLayout;
    private boolean hasLoadDataONCE = false;
    private boolean hasLoadDataTWICE = false;


    //about storage
    private SocialBean socialBean;
    //缓存的list重新生成该recycleview需要的List
    private List<NewsBean.ResultBean.DataBean> cachedData =new ArrayList<NewsBean.ResultBean.DataBean>();


    private List<SocialBean> savedlist;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    Toast.makeText(getActivity(), "存储成功", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    });


    @Override
    protected int setContentView() {

        if (NetStatusUtil.isConnected(getActivity())) {

            return R.layout.fragment_top;
        }else {
            if (null == MyApplication.getInstances().getDaoSession().getSocialBeanDao().toString()) {
                //没有缓存
                return R.layout.fragment_top_none;

            }else {
                return R.layout.fragment_top;
            }
        }
    }

    @Override
    protected void lazyLoad() {
        Log.d(TAg,"lazyLoad2");
        if (NetStatusUtil.isConnected(getActivity())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    recyclerView = getContentView().findViewById(R.id.top_recycleview);

                    refreshLayout = getContentView().findViewById(R.id.refreshLayout);
                    NewsBean topResponse = OkHttpUtil.getOkHttpResponse(url);
                    data = topResponse.getResult().getData();
                    for (int i = 0; i < 10; i++) {
                        mCutData.add(data.get(i));
                    }
                    adapter = new RecycleViewAdapter(mCutData);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            saveDataToDB(data);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                                @Override
                                public void onRefresh(RefreshLayout refreshlayout) {
                                    refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            NewsBean topResponse = OkHttpUtil.getOkHttpResponse(url);
                                            data = topResponse.getResult().getData();
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    adapter = new RecycleViewAdapter(data);
                                                    recyclerView.setAdapter(adapter);
                                                }
                                            });
                                        }
                                    }).start();
                                }
                            });
                            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore(RefreshLayout refreshlayout) {
                                    refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                                    if (hasLoadDataONCE) {
                                        Toast.makeText(getActivity(), "数据已全部加载", Toast.LENGTH_SHORT).show();
                                    } else {
                                        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(data);
                                        recyclerView.setAdapter(recycleViewAdapter);
                                        hasLoadDataONCE = true;
                                    }
                                }
                            });
                        }
                    });
                }
            }).start();
        }else {

            SocialBeanDao socialBeanDao = MyApplication.getInstances().getDaoSession().getSocialBeanDao();
            savedlist = socialBeanDao.queryBuilder()
                    .build().list();
            //没有网络
            //1.查看是否有缓存
            if (null == savedlist.toString()){
                //没有缓存
                //      view=inflater.inflate(R.layout.fragment_top_none,null,false);

            }else {
                //有缓存
                //         view = inflater.inflate(R.layout.fragment_top, null, false);
                recyclerView = getContentView().findViewById(R.id.top_recycleview);

                refreshLayout = getContentView().findViewById(R.id.refreshLayout);

                for (int i = 0; i< savedlist.size(); i++){
                    NewsBean.ResultBean.DataBean dataBean = new NewsBean.ResultBean.DataBean();
                    dataBean.setAuthor_name(savedlist.get(i).getAuthor_name());
                    dataBean.setDate(savedlist.get(i).getDate());
                    dataBean.setUrl(savedlist.get(i).getUrl());
                    dataBean.setThumbnail_pic_s(savedlist.get(i).getThumbnail_pic_s());
                    dataBean.setCategory(savedlist.get(i).getCategory());
                    dataBean.setTitle(savedlist.get(i).getTitle());


                    cachedData.add(dataBean);
                }

                List<SocialBean> dataBeanList = savedlist;
                Log.d(TAg,"savedlist :"+ savedlist.toString());
                adapter = new RecycleViewAdapter(cachedData);

                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
                refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh(RefreshLayout refreshlayout) {
                        refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                        Log.d(TAg, "onRefresh");
                        Log.d(TAg, "isMainThread :" + ToolUtils.isMainThread());
                        Toast.makeText(getActivity(), "缓存状态下数据无需更新", Toast.LENGTH_SHORT).show();

                    }
                });

                refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(RefreshLayout refreshlayout) {
                        refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                        Log.d(TAg, "onLoadMore");
                        Log.d(TAg, "onLoadMore");
                        Toast.makeText(getActivity(), "缓存状态下数据已全部加载", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        }

    }


    //保存到数据库，以便离线可以访问
    private void saveDataToDB(List<NewsBean.ResultBean.DataBean> mData) {
        MyApplication.getInstances().getDaoSession().deleteAll(SocialBean.class);
        Log.d(TAg,"go to saveDataToDB :"+mData.size());
        for (int i=0;i<mData.size();i++){
            socialBean = new SocialBean(null,
                    mData.get(i).getTitle(),
                    mData.get(i).getDate(),
                    mData.get(i).getCategory(),
                    mData.get(i).getAuthor_name(),
                    mData.get(i).getUrl(),
                    mData.get(i).getThumbnail_pic_s());
            MyApplication.getInstances().getDaoSession().insert(socialBean);

        }
        mHandler.sendEmptyMessage(1);
    }

}
