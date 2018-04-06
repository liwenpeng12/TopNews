package com.liwenpeng.topnews.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liwenpeng.topnews.MyApplication;
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.RecycleViewAdapter;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.bean.table_guoji.GuoJiBean;
import com.liwenpeng.topnews.bean.table_guoji.GuoJiBeanDao;

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
public class GuoJiFragment extends LazyLoadFragment  {
    private String url = UrlBaseConstant.GUOJI_URL;

    private RecyclerView recyclerView;
    private NewsBean topResponse;
    private static final String TAg = "TopFragment";
    private List<NewsBean.ResultBean.DataBean> data;
    private List<NewsBean.ResultBean.DataBean> mCutData =new ArrayList<NewsBean.ResultBean.DataBean>();

    //缓存的list重新生成该recycleview需要的List
    private List<NewsBean.ResultBean.DataBean> cachedData =new ArrayList<NewsBean.ResultBean.DataBean>();

    private RecycleViewAdapter adapter;
    private RefreshLayout refreshLayout;
    private boolean hasLoadDataONCE = false;
    private boolean hasLoadDataTWICE = false;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_top, null, false);
//        recyclerView = view.findViewById(R.id.top_recycleview);
//
//        refreshLayout = view.findViewById(R.id.refreshLayout);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //for the net
//                //topResponse = OkHttpUtil.getOkHttpResponse(getActivity(), UrlBaseConstant.TOP_URL, Toast.LENGTH_SHORT);
//                NewsBean topResponse = OkHttpUtil.getOkHttpResponse(url);
//                Log.d(TAg,"topResponse :"+topResponse);
//                Log.d(TAg,"getResult :"+topResponse.getResult());
//                Log.d(TAg,"getData :"+topResponse.getResult().getData());
//                data = topResponse.getResult().getData();
//                Log.d(TAg,"data :"+data.size());
//                for (int i = 0 ; i <10 ;i ++){
//                    mCutData.add(data.get(i));
//                }
//                adapter = new RecycleViewAdapter(mCutData);
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
//                        recyclerView.setAdapter(adapter);
//                        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//                            @Override
//                            public void onRefresh(RefreshLayout refreshlayout) {
//                                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//                                Log.d(TAg,"onRefresh");
//                                Log.d(TAg,"isMainThread :"+ ToolUtils.isMainThread());
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        NewsBean topResponse = OkHttpUtil.getOkHttpResponse(url);
//                                        data = topResponse.getResult().getData();
//                                        getActivity().runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                adapter = new RecycleViewAdapter(data);
//                                                recyclerView.setAdapter(adapter);
//                                            }
//                                        });
//                                    }
//                                }).start();
//
//                            }
//                        });
//                        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//                            @Override
//                            public void onLoadMore(RefreshLayout refreshlayout) {
//                                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//                                Log.d(TAg,"onLoadMore");
//                                Log.d(TAg,"onLoadMore");
//                                if (hasLoadDataONCE){
//                                    Toast.makeText(getActivity(),"数据已全部加载",Toast.LENGTH_SHORT).show();
//                                }else {
//                                    RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(data);
//                                    recyclerView.setAdapter(recycleViewAdapter);
//                                    hasLoadDataONCE = true;
//                                    //  refreshlayout.setEnableRefresh(false);
//                                }
//
//
//                            }
//                        });
//                    }
//                });
//            }
//        }).start();
//
//
//        return view;
//    }

    @Override
    protected int setContentView() {
        if (NetStatusUtil.isConnected(getActivity())) {

            return R.layout.fragment_top;
        }else {
            if (null == MyApplication.getInstances().getDaoSession().getGuoJiBeanDao().queryBuilder()
                    .build().list().toString()) {
                //没有缓存
                return R.layout.fragment_top_none;

            }else {
                return R.layout.fragment_top;
            }
        }
    }


    @Override
    protected void lazyLoad() {
        Log.d(TAg,"lazyLoad1");
        if (NetStatusUtil.isConnected(getActivity())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    recyclerView = getContentView().findViewById(R.id.top_recycleview);

                    refreshLayout = getContentView().findViewById(R.id.refreshLayout);
                    NewsBean topResponse = OkHttpUtil.getOkHttpResponse(url);
                    Log.d(TAg,"topResponse:"+topResponse);
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

            GuoJiBeanDao guoJiBeanDao = MyApplication.getInstances().getDaoSession().getGuoJiBeanDao();
            savedlist = guoJiBeanDao.queryBuilder()
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

                List<GuoJiBean> dataBeanList = savedlist;
                Log.d(TAg,"savedlist :"+ savedlist.toString());
                adapter = new RecycleViewAdapter(cachedData);

                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
                refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh(RefreshLayout refreshlayout) {
                        refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                        Log.d(TAg, "onRefresh");
                        Log.d(TAg, "isMainThread :" + ToolUtils.isMainThread());
                        Toast.makeText(getActivity(), "缓存状态下数据无需更新", Toast.LENGTH_SHORT).show();

                    }
                });

                refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(RefreshLayout refreshlayout) {
                        refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
                        Log.d(TAg, "onLoadMore");
                        Log.d(TAg, "onLoadMore");
                        Toast.makeText(getActivity(), "缓存状态下数据已全部加载", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        }

    }


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

    private GuoJiBean guoJiBean;
    private List<GuoJiBean> savedlist;

    //保存到数据库，以便离线可以访问
    private void saveDataToDB(List<NewsBean.ResultBean.DataBean> mData) {
        MyApplication.getInstances().getDaoSession().deleteAll(GuoJiBean.class);
        Log.d(TAg,"go to saveDataToDB :"+mData.size());
        for (int i=0;i<mData.size();i++){
            guoJiBean = new GuoJiBean(null,
                    mData.get(i).getTitle(),
                    mData.get(i).getDate(),
                    mData.get(i).getCategory(),
                    mData.get(i).getAuthor_name(),
                    mData.get(i).getUrl(),
                    mData.get(i).getThumbnail_pic_s());
            MyApplication.getInstances().getDaoSession().insert(guoJiBean);

        }
        mHandler.sendEmptyMessage(1);
    }

}
