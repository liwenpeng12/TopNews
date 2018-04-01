package com.liwenpeng.topnews.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liwenpeng.topnews.R;

public class SplashActivity extends AppCompatActivity {

    private CustomVideoView customVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        customVideoView = findViewById(R.id.videoview);
        Button button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });
        customVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.start_view));
        //播放
        customVideoView.start();
        //循环播放
        customVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                customVideoView.start();
            }
        });
    }
}
