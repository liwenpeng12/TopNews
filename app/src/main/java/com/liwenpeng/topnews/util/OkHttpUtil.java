package com.liwenpeng.topnews.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liwenpeng.topnews.bean.NewsBean;
import com.liwenpeng.topnews.constant.UrlBaseConstant;
import com.liwenpeng.topnews.view.MainActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * liwenpeng
 * 2018/4/1 20:51
 */
public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";

    private static NewsBean newsBean;
    private static OkHttpClient client;

    public static NewsBean  getOkHttpResponse(final Context context, String url, final int length){
        if (client == null){
            client = new OkHttpClient();
        }

        Request request =new Request.Builder().url(UrlBaseConstant.TOP_URL).build();
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
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                newsBean = null;
//                Toast.makeText(context,"OKhttp请求数据失败",length).show();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //在子线程
//
//
//                String body = response.body().string();
//                Log.d(TAG,"body :"+body);
//                //Gson解析json数据
//                Gson gson = new Gson();
//                newsBean = gson.fromJson(body, NewsBean.class);
//
//            }
//        });
        return newsBean;
    }
}
