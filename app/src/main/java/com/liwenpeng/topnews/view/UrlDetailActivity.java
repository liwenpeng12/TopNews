package com.liwenpeng.topnews.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liwenpeng.topnews.R;

public class UrlDetailActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_detail);
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


    }
}
