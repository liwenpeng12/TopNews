package com.liwenpeng.topnews.view.fragment;

import android.os.Bundle;
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

import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.RecycleViewAdapter;
import com.liwenpeng.topnews.bean.Bean;
import com.liwenpeng.topnews.bean.NewsBean;
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
public class TopFragment extends Fragment  {
    private String url = UrlBaseConstant.TOP_URL;

    private RecyclerView recyclerView;
    private NewsBean topResponse;
    private static final String TAg = "TopFragment";
    private List<NewsBean.ResultBean.DataBean> data;
    private List<NewsBean.ResultBean.DataBean> mCutData =new ArrayList<NewsBean.ResultBean.DataBean>();



    private RecycleViewAdapter adapter;
    private RefreshLayout refreshLayout;
    private boolean hasLoadDataONCE = false;
    private boolean hasLoadDataTWICE = false;


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (NetStatusUtil.isConnected(getActivity())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //for the net
                    //topResponse = OkHttpUtil.getOkHttpResponse(getActivity(), UrlBaseConstant.TOP_URL, Toast.LENGTH_SHORT);
                    NewsBean topResponse = OkHttpUtil.getOkHttpResponse(url);
                    Log.d(TAg, "topResponse :" + topResponse);
                    Log.d(TAg, "getResult :" + topResponse.getResult());
                    Log.d(TAg, "getData :" + topResponse.getResult().getData());
                    data = topResponse.getResult().getData();
                    saveDataToDB(data);
                    Log.d(TAg, "data :" + data.size());
                    for (int i = 0; i < 10; i++) {
                        mCutData.add(data.get(i));
                    }
                    adapter = new RecycleViewAdapter(mCutData);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                                @Override
                                public void onRefresh(RefreshLayout refreshlayout) {
                                    refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                                    Log.d(TAg, "onRefresh");
                                    Log.d(TAg, "isMainThread :" + ToolUtils.isMainThread());
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

                            Log.d(TAg,"top_data :"+data.toString());
                            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore(RefreshLayout refreshlayout) {
                                    refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                                    Log.d(TAg, "onLoadMore");
                                    Log.d(TAg, "onLoadMore");
                                    if (hasLoadDataONCE) {
                                        Toast.makeText(getActivity(), "数据已全部加载", Toast.LENGTH_SHORT).show();
                                    } else {
                                        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(data);
                                        recyclerView.setAdapter(recycleViewAdapter);
                                        hasLoadDataONCE = true;
                                        //  refreshlayout.setEnableRefresh(false);
                                    }
                                }
                            });
                        }
                    });
                }
            }).start();
            view = inflater.inflate(R.layout.fragment_top, null, false);

            recyclerView = view.findViewById(R.id.top_recycleview);

            refreshLayout = view.findViewById(R.id.refreshLayout);


        }else {

            //没有网络
            //1.查看是否有缓存
//            if (null ==dbBean.getCategory()){
//                //没有缓存
//                view=inflater.inflate(R.layout.fragment_top_none,null,false);
//
//            }else {
//                //有缓存
//                view = inflater.inflate(R.layout.fragment_top, null, false);
//                recyclerView = view.findViewById(R.id.top_recycleview);
//
//                refreshLayout = view.findViewById(R.id.refreshLayout);
//                for (int i=0;i<)
//
//                List<NewsBean.ResultBean.DataBean> dataBeanList = dbBean.getBeanList();
//                Log.d(TAg,"dataBeanList :"+dataBeanList.toString());
//                adapter = new RecycleViewAdapter(dataBeanList);
//
//                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//                recyclerView.setAdapter(adapter);
//                refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//                    @Override
//                    public void onRefresh(RefreshLayout refreshlayout) {
//                        refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//                        Log.d(TAg, "onRefresh");
//                        Log.d(TAg, "isMainThread :" + ToolUtils.isMainThread());
//                        Toast.makeText(getActivity(), "缓存状态下数据无需更新", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//                    @Override
//                    public void onLoadMore(RefreshLayout refreshlayout) {
//                        refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//                        Log.d(TAg, "onLoadMore");
//                        Log.d(TAg, "onLoadMore");
//                            Toast.makeText(getActivity(), "缓存状态下数据已全部加载", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//            }
//            }
        }

       return view;
    }


    //将
    @Override
    public void onStart() {
        super.onStart();

    }

    //保存到数据库，以便离线可以访问
    private void saveDataToDB(List<NewsBean.ResultBean.DataBean> mData) {
        Log.d(TAg,"go to saveDataToDB :"+mData.get(0).getAuthor_name());
       Bean Bean = new Bean();
        for (int i=0;i<mData.size();i++){
            Bean.setTitle(mData.get(i).getTitle());
            Bean.setAuthor_name(mData.get(i).getAuthor_name());
            Bean.setCategory(mData.get(i).getCategory());
            Bean.setDate(mData.get(i).getDate());
            Bean.setUrl(mData.get(i).getUrl());
            Bean.setThumbnail_pic_s(mData.get(i).getThumbnail_pic_s());
            Bean.save();
        }


    }


}
