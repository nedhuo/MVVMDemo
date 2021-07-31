package com.goldze.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * description: 自定义一个可以嵌套滚动的View
 * 滚动顺序为：父滚动View先进行滚动，父滚动View到底后子View进行滚动
 * <p>
 * 好一点的实现思路先让子View消费，子View判断父View是否滑动的顶端或底端，如果FALSE 则不消费
 * 否则，进行消费
 */
public class MultiScrollView extends ScrollView {
    public MultiScrollView(Context context) {
        super(context);
    }

    public MultiScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MultiScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

}
