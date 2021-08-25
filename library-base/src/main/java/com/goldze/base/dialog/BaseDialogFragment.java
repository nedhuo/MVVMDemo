package com.goldze.base.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.ScreenUtils;
import com.goldze.base.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.base.IBaseView;

/**
 * CreateDate: 2020/12/16 14:51
 * UpdateUser: 更新者
 * UpdateRemark: 更新说明
 */
public abstract class BaseDialogFragment<VM extends BaseViewModel, VDB extends ViewDataBinding> extends DialogFragment implements IBaseView {
    protected VM mViewModel;
    protected VDB mBinding;
    protected View mContentView;
    private ViewModelProvider mActProvider;
    private ViewModelProvider.Factory mFactory;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mContentView = mBinding.getRoot();
        mBinding.setLifecycleOwner(this);
        createViewModel();
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            initArgs(getArguments());
        }
        initView();
        initData();
        initListener();
    }

    protected void initArgs(Bundle arguments) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window == null) {
                return;
            }
           // window.getDecorView().setClipToOutline(false);
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.setLayout(ScreenUtils.getScreenWidth(), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setWindowAnimations(R.style.CommonShowDialogBottom);
            initDialogStyle(window);
        }

    }

    protected void initDialogStyle(Window window) {

    }

    private void createViewModel() {
        if (mViewModel == null) {
            Class modelClass;
            //返回直接继承的父类，包含泛型参数
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                //获取第一个泛型参数
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) createViewModel(this, modelClass);
        }
    }


//    protected  <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> clazz) {
//        return new ViewModelProvider(fragment).get(clazz);
//    }

    @Override
    public <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActProvider == null) {
            mActProvider = new ViewModelProvider(requireActivity(),);
        }
        return mActProvider.get(modelClass);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void initListener() {
    }

    public abstract void initData();
}
