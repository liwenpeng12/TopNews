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
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.adapter.TopRecycleViewAdapter;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.util.OkHttpUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    private TopRecycleViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, null, false);
        recyclerView = view.findViewById(R.id.top_recycleview);

        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Log.d(TAg,"onRefresh");

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                Log.d(TAg,"onLoadMore");
                adapter.notifyDataSetChanged();
            }
        });
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

}
