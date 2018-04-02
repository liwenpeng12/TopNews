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

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.TopRecycleViewAdapter;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.util.OkHttpUtil;

import java.util.List;

/**
 * liwenpeng
 * 2018/4/1 22:28
 */
public class TopFragment extends Fragment  {

    private RecyclerView recyclerView;
    private NewsBean topResponse;
    private static final String TAg = "TopFragment";
    private List<NewsBean.ResultBean.DataBean> data;
    private SwipeToLoadLayout swipeToLoadLayout;
    private TopRecycleViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, null, false);
        recyclerView = view.findViewById(R.id.top_recycleview);
//        swipeToLoadLayout = view.findViewById(R.id.swipeToLoadLayout);

//        swipeToLoadLayout.setOnRefreshListener(this);
//
//        swipeToLoadLayout.setOnLoadMoreListener(this);
//        autoRefresh();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //for the net
                //topResponse = OkHttpUtil.getOkHttpResponse(getActivity(), UrlBaseConstant.TOP_URL, Toast.LENGTH_SHORT);
                NewsBean topResponse = OkHttpUtil.getLocalResponse();
                Log.d(TAg,"topResponse :"+topResponse);
                Log.d(TAg,"getResult :"+topResponse.getResult());
                Log.d(TAg,"getData :"+topResponse.getResult().getData());
                data = topResponse.getResult().getData();
                adapter = new TopRecycleViewAdapter(data);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();


        return view;
    }

//    private void autoRefresh() {
//        swipeToLoadLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                swipeToLoadLayout.setRefreshing(true);
//            }
//        });
//    }

//    @Override
//    public void onLoadMore() {
//        swipeToLoadLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeToLoadLayout.setLoadingMore(false);
//
//            }
//        }, 2000);
//    }
//
//    @Override
//    public void onRefresh() {
//        swipeToLoadLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeToLoadLayout.setRefreshing(false);
////                adapter.add("REFRESH:\n" + new Date());
//
//            }
//        }, 2000);
//    }
}
