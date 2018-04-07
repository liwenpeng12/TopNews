package com.liwenpeng.topnews.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.liwenpeng.topnews.R;
import com.liwenpeng.topnews.util.NetStatusUtil;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class UrlDetailActivity extends SkinBaseActivity {

    private static final String APP_CACAHE_DIRNAME = "webview_cache";
    private static final String TAG = "UrlDetailActivity";
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_detail);
        final ProgressBar progressBar = findViewById(R.id.pb_webview);
        webView = findViewById(R.id.webview_detail);
        String intent_url = getIntent().getStringExtra("INTENT_URL");
        if (intent_url != null){
            webView.loadUrl(intent_url);
        }

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDefaultTextEncodingName("utf-8");


        if (NetStatusUtil.isConnected(getApplicationContext())) {
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        } else {
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
        }

        webView.getSettings().setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webView.getSettings().setDatabaseEnabled(true);   //开启 database storage API 功能
        webView.getSettings().setAppCacheEnabled(true);//开启 Application Caches 功能

        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        Log.d(TAG,"cacheDirPath :"+cacheDirPath);///data/user/0/com.liwenpeng.topnews/fileswebview_cache
        webView.getSettings().setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void  onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }
          //      super.onProgressChanged(view, newProgress);
            }
        });


    }
}
