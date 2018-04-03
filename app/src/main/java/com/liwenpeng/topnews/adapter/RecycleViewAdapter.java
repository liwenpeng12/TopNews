package com.liwenpeng.topnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * 如何实现分页加载？
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private final String Key = "INTENT_URL";
    private final String TAG = "RecycleViewAdapter";
    private List<NewsBean.ResultBean.DataBean> mData;
    private Context context;

    //    private  ImageView iv_2;
//    private  ImageView iv_3;

    private int ONCE = 10;
    private int page = 1;
    private int mNum = 0;
    private boolean first_in = true;

    public RecycleViewAdapter(List<NewsBean.ResultBean.DataBean> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_news_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder");

        String pic_s1 = mData.get(position).getThumbnail_pic_s();
        String pic_title = mData.get(position).getTitle();
        String date = mData.get(position).getDate();
        final String intent_url = mData.get(position).getUrl();
        String author_name = mData.get(position).getAuthor_name();
        Glide.with(context).load(pic_s1).into(holder.iv_1);
        holder.tv_title.setText(pic_title);
        holder.tv_author.setText(author_name);
        holder.top_date.setText(date);
        holder.tv_title.setOnClickListener(new View.OnClickListener() {
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
        Log.d(TAG,"getItemCount :"+mData.size());

            return  mData.size();



    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_1;
        private TextView tv_author;
        private TextView top_date;
        private TextView tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_1 = itemView.findViewById(R.id.top_pic_1);
            tv_author = itemView.findViewById(R.id.top_text_author);
            top_date = itemView.findViewById(R.id.top_text_date);
            tv_title = itemView.findViewById(R.id.top_text_title);
        }
    }




}
