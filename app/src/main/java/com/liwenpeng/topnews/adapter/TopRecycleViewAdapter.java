package com.liwenpeng.topnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.view.UrlDetailActivity;

import java.util.List;

/**
 * Created by fh on 18-4-2.
 */

public class TopRecycleViewAdapter extends RecyclerView.Adapter<TopRecycleViewAdapter.ViewHolder> {
    private final String Key = "INTENT_URL";
    private List<NewsBean.ResultBean.DataBean> mData;
    private Context context;

    private ImageView iv_1;
    //    private  ImageView iv_2;
//    private  ImageView iv_3;
    private TextView tv_author;
    private TextView top_date;
    private TextView tv_title;

    public TopRecycleViewAdapter(List<NewsBean.ResultBean.DataBean> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_news_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String pic_s1 = mData.get(position).getThumbnail_pic_s();
        String pic_title = mData.get(position).getTitle();
        String date = mData.get(position).getDate();
        final String intent_url = mData.get(position).getUrl();
        String author_name = mData.get(position).getAuthor_name();
        Glide.with(context).load(pic_s1).into(iv_1);
        tv_title.setText(pic_title);
        tv_author.setText(author_name);
        top_date.setText(date);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UrlDetailActivity.class);
                intent.putExtra(Key,intent_url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            iv_1 = itemView.findViewById(R.id.top_pic_1);
            tv_author = itemView.findViewById(R.id.top_text_author);
            top_date = itemView.findViewById(R.id.top_text_date);
            tv_title = itemView.findViewById(R.id.top_text_title);
        }
    }
}
