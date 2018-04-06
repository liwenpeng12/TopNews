package com.liwenpeng.topnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.bean.table_top.GDBean;

import java.util.ArrayList;
import java.util.List;

/**
 * liwenpeng
 * 2018/4/6 15:35
 */
public class ListSearchHistoryAdapter extends BaseAdapter {
    private List<GDBean> list = new ArrayList();
    private Context mContext;

    public ListSearchHistoryAdapter(Context context,List list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.search_list_item, null, false);
        TextView textView = inflate.findViewById(R.id.search_item_tv);
        ImageView imageView = inflate.findViewById(R.id.search_item_iv);
        Glide.with(mContext).load(list.get(i).getThumbnail_pic_s()).into(imageView);

        textView.setText(list.get(i).getTitle());
        return inflate;
    }
}
