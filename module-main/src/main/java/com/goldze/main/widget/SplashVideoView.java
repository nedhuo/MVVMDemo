package com.goldze.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class SplashVideoView extends VideoView {
    public SplashVideoView(Context context) {
        super(context, null);
    }

    public SplashVideoView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public SplashVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);
        int heigth = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, heigth);
    }
}
