package com.goldze.base;

import androidx.lifecycle.ViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ProjectName: BubbleVoice
 * Package: com.lnkj.lib_base
 * Description: BaseViewModel
 * ViewModel对象可以包含LifecycleObservers如LiveData对象，但是ViewModel对象
 * 绝不能观察对生命周期感知型可观察对象(如{@link androidx.lifecycle.LiveData}对象)的更改，同时ViewModel
 * 绝不能引用视图、Lifecycle或可能存储对Activity上下文引用的任何类
 * Author: 姚闻达
 * CreateDate: 2020/12/16 14:05
 * UpdateUser: 更新者
 * UpdateDate: 2020/12/16 14:05
 * UpdateRemark: 更新说明
 * Version: 1.0
 */
public class BaseViewModel<T> extends ViewModel {
    private T repository;

    public BaseViewModel() {
        createRepository();
    }

    private void createRepository() {
        Class repositoryClazz;
        Type type = getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType) {
            repositoryClazz = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
          //  repositoryClazz = DefaultRepository.class;
        }
        try {
            repository = (T) repositoryClazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public T getRepository() {
        return repository;
    }
}
