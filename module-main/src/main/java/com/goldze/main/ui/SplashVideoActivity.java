package com.goldze.main.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;


import androidx.annotation.Nullable;

import com.blankj.utilcode.util.BarUtils;
import com.goldze.main.R;

public class SplashVideoActivity extends Activity {

    private VideoView mVideoView;
    private MediaPlayer mVoice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarVisibility(this,false);
        setContentView(R.layout.activity_splash_video);

        initView();
        initData();
    }

    private void initView() {
        mVideoView = findViewById(R.id.videoView);
    }

    private void initData() {
        // 加载视频资源
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ad_video));
        //播放
        mVideoView.start();
        //循环播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //控制声音的变量
                mVoice = mp;
                //mBtStart.setVisibility(View.VISIBLE);                    //一轮视频结束后,显示欢迎进入按钮
                mVideoView.start();
            }
        });

    }
}
