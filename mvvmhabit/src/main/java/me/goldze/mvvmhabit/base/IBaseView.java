package me.goldze.mvvmhabit.base;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2017/6/15.
 */

public interface IBaseView {
    /**
     * 初始化界面传递参数
     */
    void initParam();
    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();

    /**
     * 获取Activity范围共享的ViewModel
     * @param modelClass
     * @param <T>
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    default <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass){
        return null;
    }

    default ViewModelProvider getAppViewModelProvider(@NonNull Activity activity){
        return null;
    }
}
