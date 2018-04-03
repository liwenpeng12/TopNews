package com.liwenpeng.topnews.util;

import android.util.Log;

import com.google.gson.Gson;
import com.liwenpeng.topnews.bean.NewsBean;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * liwenpeng
 * 2018/4/1 20:51
 */
public class OkHttpUtil {
     static final String TAG = "OkHttpUtil";
     static NewsBean newsBean;
     static OkHttpClient client;
    public static NewsBean  getOkHttpResponse(String url){
        if (client == null){
            client = new OkHttpClient();
        }
        Request request =new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            Log.d(TAG,"body :"+body);
            //Gson解析json数据
            Gson gson = new Gson();
            newsBean = gson.fromJson(body, NewsBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsBean;
    }
}
