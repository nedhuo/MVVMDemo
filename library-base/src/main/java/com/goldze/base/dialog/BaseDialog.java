package com.goldze.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.goldze.base.R;

public abstract class BaseDialog<VDM extends ViewDataBinding> extends Dialog {

    protected VDM mBinding;
    protected View mContentView;

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.BaseDialogStyle);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mBinding = DataBindingUtil.bind(LayoutInflater.from(context).inflate(getLayoutId(), null, false));
        if (mBinding != null) {
            mContentView = mBinding.getRoot();
            setContentView(mContentView);
        }
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initView();
        initStyle(getWindow());
        initListener();
        initData();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    /**
     * 设置Dialog样式
     */
    public void initStyle(Window window) {
        WindowManager wm = window.getWindowManager();
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        Point point = new Point();
        display.getSize(point);
        // lp.width = (int) (display.getWidth() * 0.85);
        lp.width = (int) (point.x * 0.85);
        window.setAttributes(lp);
    }

    public void initListener() {

    }
}